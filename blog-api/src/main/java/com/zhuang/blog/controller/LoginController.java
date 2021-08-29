package com.zhuang.blog.controller;

import com.zhuang.blog.entity.LoginParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 22:18
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录操作
     *
     * @param loginParam 登录参数
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }
}
