package com.zhuang.blog.service;

import com.zhuang.blog.entity.CommentParam;
import com.zhuang.blog.entity.Result;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 14:58
 * @Version 1.0
 */
public interface CommentsService {

    /**
     * 根据文章ID获取评论数据
     *
     * @param articleId
     * @return
     */
    Result commentsByArticleId(Long articleId);

    /**
     * 评论功能
     *
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
