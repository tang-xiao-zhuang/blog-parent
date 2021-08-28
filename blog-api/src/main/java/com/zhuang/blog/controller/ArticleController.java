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
    public Result listArchives(@RequestBody PageParam pageParam) {
        return articleService.listArchives(pageParam);
    }
}
