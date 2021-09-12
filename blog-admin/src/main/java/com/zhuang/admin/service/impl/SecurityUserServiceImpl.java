package com.zhuang.admin.service.impl;

import com.zhuang.admin.pojo.Admin;
import com.zhuang.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author tangqingbo
 * @Date 2021/9/12 11:23
 * @Version 1.0
 */
@Component
public class SecurityUserServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    /**
     * 登录的时候会把username传递到这里
     * 我们需要通过username查询用户表(本项目用户信息存在与admin表),如果用户存在,将密码告诉告诉springSecurity
     * 如果不存在,返回null,认证失败
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findAdminByUsername(username);
        if (admin == null) {
            //登录失败
            return null;
        }
        UserDetails userDetails = new User(username, admin.getPassword(), new ArrayList<>());
        return userDetails;
    }
}
