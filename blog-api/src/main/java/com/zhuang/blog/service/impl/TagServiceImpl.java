package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.TagDao;
import com.zhuang.blog.pojo.Tag;
import com.zhuang.blog.service.TagService;
import com.zhuang.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/8/28 11:44
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    /**
     * 根据文章ID获取对应的标签信息
     *
     * @param articleId 文章ID
     * @return
     */
    @Override
    public List<TagVo> findByArticleId(Long articleId) {
        List<Tag> tags = tagDao.findByArticleId(articleId);
        return copyList(tags);
    }

    /**
     * 首页-最热标签
     *
     * @param limit
     * @return
     */
    @Override
    public List<TagVo> listHotTags(int limit) {
        List<Tag> tags = tagDao.listHotTags(limit);
        return copyList(tags);
    }

    /**
     * 类型转换
     *
     * @param tag
     * @return
     */
    public TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }

    /**
     * 类型转换
     *
     * @param tagList
     * @return
     */
    public List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        tagList.forEach(t -> tagVoList.add(copy(t)));
        return tagVoList;
    }
}
