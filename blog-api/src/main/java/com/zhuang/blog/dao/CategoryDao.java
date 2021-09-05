package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.Category;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 11:55
 * @Version 1.0
 */
public interface CategoryDao {
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
    List<Category> findAll();
}
