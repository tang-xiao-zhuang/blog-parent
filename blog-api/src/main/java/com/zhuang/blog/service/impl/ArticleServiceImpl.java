package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.ArticleDao;
import com.zhuang.blog.pojo.Article;
import com.zhuang.blog.service.ArticleService;
import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.SysUserService;
import com.zhuang.blog.service.TagService;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

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
        List<ArticleVo> articleVos = copyList(articles, true, true);
        return Result.success(articleVos);
    }

    /**
     * 类型转换
     *
     * @param articles 数据源
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles, boolean isTag, boolean isAuthor) {
        List<ArticleVo> voList = new ArrayList<>();
        articles.forEach(a -> voList.add(copy(a, isTag, isAuthor)));
        return voList;
    }

    /**
     * 类型转换
     *
     * @param article  文章信息
     * @param isTag    是否需要tag标签
     * @param isAuthor 是否需要作者信息
     * @return
     */
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm:ss"));
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findById(authorId).getNickname());
        }
        return articleVo;
    }
}
