package com.zhuang.blog.pojo;

import lombok.Data;

/**
 * @author tangqingbo
 */
@Data
public class Article {

    public static final int ARTICLE_TOP = 1;

    public static final int ARTICLE_COMMON = 0;

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String summary;

    /**
     * 评论数量
     */
    private int commentCounts;

    /**
     * 浏览数量
     */
    private int viewCounts;

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 内容id
     */
    private Long bodyId;

    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private int weight = ARTICLE_COMMON;


    /**
     * 创建时间
     */
    private Long createDate;
}

