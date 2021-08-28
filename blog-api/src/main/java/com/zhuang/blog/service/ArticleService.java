package com.zhuang.blog.service;

import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:37
 * @Version 1.0
 */
public interface ArticleService {

    /**
     * 首页文章列表
     * @param pageParam
     * @return
     */
    Result listArchives(PageParam pageParam);
}
