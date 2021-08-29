package com.zhuang.blog.service;

import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.SysUser;

/**
 * @Author tangqingbo
 * @Date 2021/8/28 12:04
 * @Version 1.0
 */
public interface SysUserService {
    /**
     * 根据ID查询用户名
     *
     * @param id 作者ID
     * @return
     */
    SysUser findById(Long id);

    /**
     * 根据用户名密码获取用户信息
     *
     * @param account  账户名
     * @param password 密码
     * @return
     */
    SysUser findUserByAccountAndPassword(String account, String password);

    /**
     * 根据token获取用户信息
     *
     * @param token token信息
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    Result logout(String token);
}
