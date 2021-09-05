package com.zhuang.blog.controller;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.TagService;
import com.zhuang.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 10:29
 * @Version 1.0
 */
@RestController
public class TagsController {
    @Autowired
    private TagService tagService;

    /**
     * 首页-最热标签
     *
     * @return
     */
    @GetMapping("/tags/hot")
    public Result listHotTags() {
        //显示最热标签条数
        int limit = 6;
        List<TagVo> tagVoList = tagService.listHotTags(limit);
        return Result.success(tagVoList);
    }

    /**
     * 所有文章标签
     *
     * @return
     */
    @GetMapping("/tags")
    public Result listTags() {
        return tagService.findAll();
    }
}
