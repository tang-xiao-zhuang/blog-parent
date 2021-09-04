package com.zhuang.blog.dao;

import com.zhuang.blog.entity.Articles;
import com.zhuang.blog.pojo.Article;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:17
 * @Version 1.0
 */
public interface ArticleDao {

    /**
     * 分页查询文章列表
     *
     * @param start limit起始位
     * @param end   limit结束位
     * @return
     */
    List<Article> listArticle(int start, int end);

    /**
     * 最热文章
     *
     * @param limit 限制系数
     * @return
     */
    List<Article> hotArticle(int limit);

    /**
     * 最新文章
     *
     * @param limit 限制系数
     * @return
     */
    List<Article> newArticles(int limit);

    /**
     * 文章归档
     *
     * @return
     */
    List<Articles> listArchives();

    /**
     * 根据ID获取文章信息
     *
     * @param articleId
     * @return
     */
    Article findById(Long articleId);
}
