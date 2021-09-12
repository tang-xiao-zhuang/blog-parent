package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.CategoryDao;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.Category;
import com.zhuang.blog.service.CategoryService;
import com.zhuang.blog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询所有文章分类
     *
     * @return
     */
    @Override
    public Result findAll() {
        List<Category> categoryList = categoryDao.findAll();
        return Result.success(copyList(categoryList));
    }

    /**
     * 查询所有文章分类
     *
     * @return
     */
    @Override
    public Result categoryDetail() {
        List<Category> categoryList = categoryDao.findAll();
        return Result.success(categoryList);
    }

    /**
     * 根据分类ID获取信息
     *
     * @param id
     * @return
     */
    @Override
    public Result categoriesDetailById(Long id) {
        Category category = categoryDao.findById(id);
        return Result.success(category);
    }

    /**
     * 类型转换
     *
     * @param categoryList
     * @return
     */
    public List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        categoryList.forEach(c -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(c, categoryVo);
            categoryVoList.add(categoryVo);
        });
        return categoryVoList;
    }
}
