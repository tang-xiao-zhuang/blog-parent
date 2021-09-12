package com.zhuang.admin.service.impl;

import com.zhuang.admin.dao.AdminDao;
import com.zhuang.admin.pojo.Admin;
import com.zhuang.admin.pojo.Permission;
import com.zhuang.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/12 11:26
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public Admin findAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }

    /**
     * 根据adminId查找对应的权限信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findPermissionByAdminId(Long id) {
        return adminDao.findPermissionByAdminId(id);
    }
}
