package com.zhuang.blog.service;

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
}
