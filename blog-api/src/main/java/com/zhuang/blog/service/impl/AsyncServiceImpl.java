package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.ArticleDao;
import com.zhuang.blog.pojo.Article;
import com.zhuang.blog.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 14:32
 * @Version 1.0
 */
@Component
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 增加浏览量
     *
     * @param article
     */
    @Override
    @Async
    public void addViewCount(Article article) {
        article.setViewCounts(article.getViewCounts() + 1);
        articleDao.updateViewCount(article.getId());
    }
}
