package com.zhuang.blog.service;

import com.zhuang.blog.entity.LoginParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.SysUser;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 22:20
 * @Version 1.0
 */
public interface LoginService {

    /**
     * 登录操作
     *
     * @param loginParam 登录参数信息
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    SysUser checkToken(String token);

    /**
     * 注册
     *
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
