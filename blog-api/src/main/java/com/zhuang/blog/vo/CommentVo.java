package com.zhuang.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private Long createDate;

    private String createDateStr;

    private Integer level;

    private UserVo toUser;
}