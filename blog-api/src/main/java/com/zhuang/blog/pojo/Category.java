package com.zhuang.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 10:21
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
