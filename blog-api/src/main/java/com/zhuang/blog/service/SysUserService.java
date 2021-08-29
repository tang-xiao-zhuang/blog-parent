package com.zhuang.blog.service;

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
}
