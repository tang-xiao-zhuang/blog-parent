package com.zhuang.blog.controller;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 22:51
 * @Version 1.0
 */
@RestController
@Api(tags = "用户信息相关接口")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据token获取用户信息
     *
     * @param token token信息
     * @return
     */
    @GetMapping("/users/currentUser")
    public Result currentUser(@RequestHeader(value = "Authorization") String token) {
        return sysUserService.findUserByToken(token);
    }

    /**
     * 退出登录
     *
     * @param token token信息
     * @return
     */
    @GetMapping("/logout")
    public Result logout(@RequestHeader(value = "Authorization") String token) {
        return sysUserService.logout(token);
    }
}
