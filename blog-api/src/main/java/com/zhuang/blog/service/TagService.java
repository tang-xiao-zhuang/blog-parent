package com.zhuang.blog.service;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.vo.TagVo;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/28 11:43
 * @Version 1.0
 */
public interface TagService {
    /**
     * 根据文章ID获取对应的标签信息
     *
     * @param articleId 文章ID
     * @return
     */
    List<TagVo> findByArticleId(Long articleId);

    /**
     * 首页-最热标签
     *
     * @param limit
     * @return
     */
    List<TagVo> listHotTags(int limit);

    /**
     * 所有文章标签
     *
     * @return
     */
    Result findAll();

    /**
     * 查询所有的标签
     *
     * @return
     */
    Result tagsDetail();
}
