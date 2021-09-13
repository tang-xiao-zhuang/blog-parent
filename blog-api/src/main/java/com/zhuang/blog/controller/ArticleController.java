package com.zhuang.blog.controller;

import com.zhuang.blog.entity.ArticleParam;
import com.zhuang.blog.service.ArticleService;
import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.vo.ArticleVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:23
 * @Version 1.0
 */
@RestController
@Api(tags = "文章信息相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据文章ID获取文章详情
     *
     * @param articleId
     * @return
     */
    @PostMapping("/articles/view/{articleId}")
    public Result findArticleBodyById(@PathVariable(value = "articleId") Long articleId) {
        ArticleVo articleVo = articleService.findArticleBodyById(articleId);
        return Result.success(articleVo);
    }

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

    /**
     * 文章发布
     *
     * @param articleParam
     * @return
     */
    @PostMapping("/articles/publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
