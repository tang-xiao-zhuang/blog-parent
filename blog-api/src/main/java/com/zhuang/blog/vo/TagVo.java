package com.zhuang.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/8/28 00:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    private Long id;
    private String tagName;
}
