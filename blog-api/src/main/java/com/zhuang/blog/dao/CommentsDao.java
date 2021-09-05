package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.Comment;
import com.zhuang.blog.vo.CommentVo;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 23:37
 * @Version 1.0
 */
public interface CommentsDao {
    /**
     * 根据文章ID获取评论数据
     *
     * @param articleId 文章ID
     * @return
     */
    List<CommentVo> findByArticleId(Long articleId);

    /**
     * 保存评论
     *
     * @param comment
     */
    void comment(Comment comment);
}
