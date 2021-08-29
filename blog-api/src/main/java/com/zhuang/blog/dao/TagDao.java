package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.Tag;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:21
 * @Version 1.0
 */
public interface TagDao {

    /**
     * 根据文章ID获取标签信息
     *
     * @param articleId 文章ID
     * @return
     */
    List<Tag> findByArticleId(Long articleId);

    /**
     * 最热标签
     *
     * @param limit 限制条数
     * @return
     */
    List<Tag> listHotTags(int limit);
}
