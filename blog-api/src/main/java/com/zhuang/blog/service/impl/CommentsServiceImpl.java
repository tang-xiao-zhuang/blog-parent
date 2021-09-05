package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.CommentsDao;
import com.zhuang.blog.entity.CommentParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.Comment;
import com.zhuang.blog.pojo.SysUser;
import com.zhuang.blog.service.CommentsService;
import com.zhuang.blog.utils.UserThreadLocalUtil;
import com.zhuang.blog.vo.CommentVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 14:58
 * @Version 1.0
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    /**
     * 根据文章ID获取评论数据
     *
     * @param articleId
     * @return
     */
    @Override
    public Result commentsByArticleId(Long articleId) {
        List<CommentVo> commentVos = commentsDao.findByArticleId(articleId);
        commentVos.forEach(c -> c.setCreateDateStr(new DateTime(c.getCreateDate()).toString("yyyy-MM-dd HH:mm")));
        return Result.success(commentVos);
    }

    /**
     * 评论功能
     *
     * @param commentParam
     * @return
     */
    @Override
    @Transactional
    public Result comment(CommentParam commentParam) {
        Comment comment = new Comment();
        //获取当前用户信息
        SysUser sysUser = UserThreadLocalUtil.get();
        comment.setAuthorId(sysUser.getId());
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        Long parentId = commentParam.getParent();
        comment.setParentId(parentId == null ? 0 : parentId);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        comment.setCreateDate(System.currentTimeMillis());
        //判断等级
        if (parentId == null || parentId == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        commentsDao.comment(comment);
        return Result.success(null);
    }
}
