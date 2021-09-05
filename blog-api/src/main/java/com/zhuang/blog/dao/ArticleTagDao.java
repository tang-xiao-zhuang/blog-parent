package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.ArticleTag;

/**
 * @Author tangqingbo
 * @Date 2021/9/5 21:31
 * @Version 1.0
 */
public interface ArticleTagDao {
    /**
     * 保存数据
     *
     * @param articleTag
     */
    void save(ArticleTag articleTag);
}
