package com.zhuang.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}

