package com.zhuang.blog.service;

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
    String findById(Long id);
}
