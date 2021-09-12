package com.zhuang.blog.controller;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tangqingbo
 * @Date 2021/9/5 20:23
 * @Version 1.0
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有文章分类
     *
     * @return
     */
    @GetMapping("/categorys")
    public Result listCategory() {
        return categoryService.findAll();
    }

    /**
     * 查询所有文章分类
     *
     * @return
     */
    @GetMapping("/categorys/detail")
    public Result categoryDetail() {
        return categoryService.categoryDetail();
    }

    /**
     * 根据分类ID获取信息
     *
     * @param id
     * @return
     */
    @GetMapping("/category/detail/{id}")
    public Result categoriesDetailById(@PathVariable(value = "id") Long id) {
        return categoryService.categoriesDetailById(id);
    }
}
