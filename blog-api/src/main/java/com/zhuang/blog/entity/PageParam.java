package com.zhuang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    private int page = 1;
    private int pageSize = 10;
}
