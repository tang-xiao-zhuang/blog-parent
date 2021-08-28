package com.zhuang.blog.dao;

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
    List<Article> listArchives(int start, int end);
}
