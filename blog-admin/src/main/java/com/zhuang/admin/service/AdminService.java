package com.zhuang.admin.service;

import com.zhuang.admin.pojo.Admin;
import com.zhuang.admin.pojo.Permission;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/12 11:26
 * @Version 1.0
 */
public interface AdminService {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    Admin findAdminByUsername(String username);

    /**
     * 根据adminId查找对应的权限信息
     *
     * @param id
     * @return
     */
    List<Permission> findPermissionByAdminId(Long id);
}
