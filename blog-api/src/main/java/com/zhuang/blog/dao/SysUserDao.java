package com.zhuang.blog.dao;

import com.zhuang.blog.pojo.SysUser;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:22
 * @Version 1.0
 */
public interface SysUserDao {
    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
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
     * 根据账户查找用户
     *
     * @param account 账户名
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 新增用户
     *
     * @param sysUser
     */
    void save(SysUser sysUser);
}
