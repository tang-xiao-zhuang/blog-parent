package com.zhuang.blog.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QiNiuUtils {

    @Value("${qiniu.image.access_key}")
    private String accessKey;

    @Value("${qiniu.image.secret_key}")
    private String secretKey;

    @Value("${qiniu.image.bucket}")
    private String bucket;

    @Value("${qiniu.image.domain}")
    private String domain;

    /**
     * 获取七牛云上的所有图片
     */
    public List<String> listFile() {
        BucketManager bucketManager = getBucketManager();
        //列举空间文件列表,图片的仓库(空间名),第二个参数,文件名前缀过滤,""代理所有
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        List<String> imageFiles = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                //item.key==>文件名
                imageFiles.add(item.key);
                System.out.println(item.key);
            }
        }
        return imageFiles;
    }

    /**
     * 批量删除
     *
     * @param filenames 需要删除的文件名列表
     * @return 删除成功的文件名列表
     */
    public List<String> removeFiles(String... filenames) {
        // 删除成功的文件名列表
        List<String> removeSuccessList = new ArrayList<String>();
        if (filenames.length > 0) {
            // 创建仓库管理器
            BucketManager bucketManager = getBucketManager();
            // 创建批处理器
            BucketManager.Batch batch = new BucketManager.Batch();
            // 批量删除多个文件
            batch.delete(bucket, filenames);
            try {
                // 获取服务器的响应
                Response res = bucketManager.batch(batch);
                // 获得批处理的状态
                BatchStatus[] batchStatuses = res.jsonToObject(BatchStatus[].class);
                for (int i = 0; i < filenames.length; i++) {
                    BatchStatus status = batchStatuses[i];
                    String key = filenames[i];
                    System.out.print(key + "\t");
                    if (status.code == 200) {
                        removeSuccessList.add(key);
                        System.out.println("delete success");
                    } else {
                        System.out.println("delete failure");
                    }
                }
            } catch (QiniuException e) {
                e.printStackTrace();
                throw new RuntimeException("图片上传失败");
            }
        }
        return removeSuccessList;
    }

    public void uploadFile(String localFilePath, String savedFilename) {
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(localFilePath, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(String.format("key=%s, hash=%s", putRet.key, putRet.hash));
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException("图片上传失败");
        }
    }

    /**
     * 文件上传
     *
     * @param bytes
     * @param savedFilename
     */
    public void uploadViaByte(byte[] bytes, String savedFilename) {
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(bytes, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException("图片上传失败");
        }
    }

    private String getToken() {
        // 创建授权
        Auth auth = Auth.create(accessKey, secretKey);
        // 获得认证后的令牌
        return auth.uploadToken(bucket);
    }

    private UploadManager getUploadManager() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //构建上传管理器
        return new UploadManager(cfg);
    }

    private BucketManager getBucketManager() {
        // 创建授权信息
        Auth auth = Auth.create(accessKey, secretKey);
        // 创建操作某个仓库的管理器
        return new BucketManager(auth, new Configuration(Zone.zone2()));
    }

}