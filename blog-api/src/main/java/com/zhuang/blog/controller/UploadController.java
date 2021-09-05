package com.zhuang.blog.controller;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.utils.QiNiuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author tangqingbo
 * @Date 2021/9/5 23:30
 * @Version 1.0
 */
@RestController
public class UploadController {

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Value("${qiniu.image.domain}")
    private String domain;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        byte[] bytes = file.getBytes();
        qiNiuUtils.uploadViaByte(bytes, fileName);
        return Result.success(domain + fileName);
    }
}
