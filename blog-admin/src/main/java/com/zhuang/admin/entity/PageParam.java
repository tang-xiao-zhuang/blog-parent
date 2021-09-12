package com.zhuang.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 22:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
