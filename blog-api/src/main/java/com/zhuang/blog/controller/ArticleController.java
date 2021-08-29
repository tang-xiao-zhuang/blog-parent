package com.zhuang.blog.controller;

import com.zhuang.blog.service.ArticleService;
import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:23
 * @Version 1.0
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页-文章列表
     *
     * @param pageParam 页码参数
     */
    @PostMapping("/articles")
    public Result listArticle(@RequestBody PageParam pageParam) {
        return articleService.listArticle(pageParam);
    }

    /**
     * 最热文章
     *
     * @return
     */
    @PostMapping("/articles/hot")
    public Result hotArticle() {
        int limit = 3;
        return articleService.hotArticle(limit);
    }

    /**
     * 最新文章
     *
     * @return
     */
    @PostMapping("/articles/new")
    public Result newArticles() {
        int limit = 3;
        return articleService.newArticles(limit);
    }

    /**
     * 文章归档
     *
     * @return
     */
    @PostMapping("/articles/listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }
}
