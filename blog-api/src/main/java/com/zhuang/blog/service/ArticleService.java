package com.zhuang.blog.service;

import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.vo.ArticleVo;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:37
 * @Version 1.0
 */
public interface ArticleService {

    /**
     * 首页文章列表
     *
     * @param pageParam
     * @return
     */
    Result listArticle(PageParam pageParam);

    /**
     * 最热文章
     *
     * @param limit 限制系数
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     *
     * @param limit 限制系数
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     *
     * @return
     */
    Result listArchives();

    /**
     * 根据文章ID获取文章详情
     *
     * @param articleId
     * @return
     */
    ArticleVo findArticleBodyById(Long articleId);
}
