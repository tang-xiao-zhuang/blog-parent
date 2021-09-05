package com.zhuang.blog.controller;

import com.zhuang.blog.entity.CommentParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 14:58
 * @Version 1.0
 */
@RestController
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    /**
     * 根据文章ID获取评论数据
     *
     * @param articleId 文章ID
     * @return
     */
    @GetMapping("/comments/article/{articleId}")
    public Result comments(@PathVariable(value = "articleId") Long articleId) {
        return commentsService.commentsByArticleId(articleId);
    }

    /**
     * 评论功能
     *
     * @param commentParam
     * @return
     */
    @PostMapping("/comments/create/change")
    public Result comment(@RequestBody CommentParam commentParam) {
        return commentsService.comment(commentParam);
    }
}
