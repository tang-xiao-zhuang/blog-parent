package com.zhuang.blog.service;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.Category;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 11:54
 * @Version 1.0
 */
public interface CategoryService {
    /**
     * 根据ID获取类别信息
     *
     * @param categoryId
     * @return
     */
    Category findById(Long categoryId);

    /**
     * 查询所有文章分类
     *
     * @return
     */
    Result findAll();

    /**
     * 查询所有文章分类
     *
     * @return
     */
    Result categoryDetail();

    /**
     * 根据分类ID获取信息
     *
     * @param id
     * @return
     */
    Result categoriesDetailById(Long id);
}
