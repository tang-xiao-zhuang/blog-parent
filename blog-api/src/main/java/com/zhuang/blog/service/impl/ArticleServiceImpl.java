package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.ArticleBodyDao;
import com.zhuang.blog.dao.ArticleDao;
import com.zhuang.blog.dao.ArticleTagDao;
import com.zhuang.blog.entity.ArticleBodyParam;
import com.zhuang.blog.entity.ArticleParam;
import com.zhuang.blog.entity.PageParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.*;
import com.zhuang.blog.service.*;
import com.zhuang.blog.utils.UserThreadLocalUtil;
import com.zhuang.blog.vo.ArticleBodyVo;
import com.zhuang.blog.vo.ArticleVo;
import com.zhuang.blog.vo.CategoryVo;
import com.zhuang.blog.vo.TagVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ArticleBodyDao articleBodyDao;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ArticleTagDao articleTagDao;

    /**
     * 首页文章列表
     *
     * @param pageParam
     * @return
     */
    @Override
    public Result listArticle(PageParam pageParam) {
        int page = pageParam.getPage();
        int pageSize = pageParam.getPageSize();
        List<Article> articles = articleDao.listArticle((page - 1) * pageSize, pageSize);
        List<ArticleVo> articleVos = copyList(articles, true, true);
        return Result.success(articleVos);
    }

    /**
     * 最热文章
     *
     * @param limit 限制系数
     * @return
     */
    @Override
    public Result hotArticle(int limit) {
        List<Article> articles = articleDao.hotArticle(limit);
        return Result.success(copyList(articles, false, false));
    }

    /**
     * 最新文章
     *
     * @param limit 限制系数
     * @return
     */
    @Override
    public Result newArticles(int limit) {
        List<Article> articles = articleDao.newArticles(limit);
        return Result.success(copyList(articles, false, false));
    }

    /**
     * 文章归档
     *
     * @return
     */
    @Override
    public Result listArchives() {
        return Result.success(articleDao.listArchives());
    }

    /**
     * 根据文章ID获取文章详情
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleVo findArticleBodyById(Long articleId) {
        Article article = articleDao.findById(articleId);
        //浏览量+1
        asyncService.addViewCount(article);
        return copy(article, true, true, true, true);
    }

    /**
     * 文章发布
     *
     * @param articleParam
     * @return
     */
    @Override
    @Transactional
    public Result publish(ArticleParam articleParam) {
        //获取用户信息
        SysUser user = UserThreadLocalUtil.get();
        Article article = new Article();
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(0);
        article.setAuthorId(user.getId());
        //先设置,后修改
        article.setBodyId(-1L);
        article.setCategoryId(articleParam.getCategory().getId());
        /**
         * 保存article信息
         */
        articleDao.save(article);

        List<TagVo> tags = articleParam.getTags();
        tags.forEach(t -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(t.getId());
            /**
             * 保存ArticleTag
             */
            articleTagDao.save(articleTag);
        });

        ArticleBodyParam body = articleParam.getBody();
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(body.getContent());
        articleBody.setContentHtml(body.getContentHtml());
        /**
         * 保存ArticleBody
         */
        articleBodyDao.save(articleBody);

        //获取ArticleBody新增后的id,去修改article保存的bodyId
        article.setBodyId(articleBody.getId());
        /**
         * 修改article信息
         */
        articleDao.updateArticleBodyById(article);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        return Result.success(articleVo);
    }

    /**
     * 类型转换
     *
     * @param articles 数据源
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles, boolean isTag, boolean isAuthor) {
        List<ArticleVo> voList = new ArrayList<>();
        articles.forEach(a -> voList.add(copy(a, isTag, isAuthor, false, false)));
        return voList;
    }

    /**
     * 类型转换
     *
     * @param articles 数据源
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles, boolean isTag, boolean isAuthor, boolean isBody) {
        List<ArticleVo> voList = new ArrayList<>();
        articles.forEach(a -> voList.add(copy(a, isTag, isAuthor, isBody, false)));
        return voList;
    }

    /**
     * 类型转换
     *
     * @param articles 数据源
     * @return
     */
    private List<ArticleVo> copyList(List<Article> articles, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<ArticleVo> voList = new ArrayList<>();
        articles.forEach(a -> voList.add(copy(a, isTag, isAuthor, isBody, isCategory)));
        return voList;
    }

    /**
     * 类型转换
     *
     * @param article  文章信息
     * @param isTag    是否需要tag标签
     * @param isAuthor 是否需要作者信息
     * @param isBody   是否需要文章body体
     * @return
     */
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
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
        if (isBody) {
            ArticleBodyVo articleBody = findArticleBody(article.getId());
            articleVo.setBody(articleBody);
        }
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            CategoryVo categoryVo = findCategory(categoryId);
            articleVo.setCategory(categoryVo);
        }
        return articleVo;
    }

    /**
     * 根据类别ID获取类别信息
     *
     * @param categoryId
     * @return
     */
    private CategoryVo findCategory(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    /**
     * 根据文章ID获取文章详情
     *
     * @param id 文章ID
     * @return
     */
    private ArticleBodyVo findArticleBody(Long id) {
        ArticleBody articleBody = articleBodyDao.findByArticleId(id);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
