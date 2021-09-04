package com.zhuang.blog.service;

import com.zhuang.blog.pojo.Article;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 14:31
 * @Version 1.0
 */
public interface AsyncService {
    /**
     * 增加浏览量
     *
     * @param article
     */
    void addViewCount(Article article);
}
