package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.ArticleDao;
import com.zhuang.blog.pojo.Article;
import com.zhuang.blog.service.ArticleService;
import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.vo.ArticleVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:37
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 首页文章列表
     *
     * @param pageParam
     * @return
     */
    @Override
    public Result listArchives(PageParam pageParam) {
        int page = pageParam.getPage();
        int pageSize = pageParam.getPageSize();
        List<Article> articles = articleDao.listArchives((page - 1) * pageSize, pageSize);
        List<ArticleVo> articleVos = copyList(articles);
        return Result.success(articleVos);
    }

    /**
     * 类型转换
     *
     * @param articles 数据源
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles) {
        List<ArticleVo> voList = new ArrayList<>();
        articles.forEach(a -> voList.add(copy(a)));
        return voList;
    }

    /**
     * 类型转换
     *
     * @param article
     * @return
     */
    private ArticleVo copy(Article article) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        return articleVo;
    }
}
