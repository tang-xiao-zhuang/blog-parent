package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.ArticleBody;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 11:32
 * @Version 1.0
 */
public interface ArticleBodyDao {
    /**
     * 根据文章ID获取文章详情数据
     *
     * @param articleId
     * @return
     */
    ArticleBody findByArticleId(Long articleId);
}
