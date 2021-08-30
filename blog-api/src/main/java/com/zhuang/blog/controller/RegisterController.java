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
 * @Date 2021/8/30 22:46
 * @Version 1.0
 */
@RestController
public class RegisterController {

    @Autowired
    private LoginService loginService;

    /**
     * 注册操作
     *
     * @param loginParam 注册信息
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }
}
