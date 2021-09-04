package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.CategoryDao;
import com.zhuang.blog.pojo.Category;
import com.zhuang.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 11:54
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 根据ID获取类别信息
     *
     * @param categoryId
     * @return
     */
    @Override
    public Category findById(Long categoryId) {
        return categoryDao.findById(categoryId);
    }
}
