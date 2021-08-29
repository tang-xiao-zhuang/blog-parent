package com.zhuang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 12:27
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articles {
    /**
     * 文章归档年份
     */
    private Integer year;

    /**
     * 文章归档月份
     */
    private Integer month;

    /**
     * 所属年月文章发布篇数
     */
    private Long count;
}
