package com.zhuang.admin.service.impl;

import com.zhuang.admin.pojo.Admin;
import com.zhuang.admin.pojo.Permission;
import com.zhuang.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author tangqingbo
 * @Date 2021/9/12 11:37
 * @Version 1.0
 */
@Service
public class AuthService {

    @Autowired
    private AdminService adminService;

    public boolean auth(HttpServletRequest request, Authentication authentication) {
        //权限认证
        //获取请求路径
        String uri = request.getRequestURI();
        //获取当前用户信息
        Object principal = authentication.getPrincipal();
        //anonymousUser 匿名用户
        if (principal == null || "anonymousUser".equals(principal)) {
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        Admin admin = adminService.findAdminByUsername(userDetails.getUsername());
        if (admin == null) {
            return false;
        }
        Long id = admin.getId();
        if (id == 1) {
            //认为是超级管理员
            return true;
        }
        List<Permission> permissionList = adminService.findPermissionByAdminId(id);
        //排除?号
        String realUri = uri.split("\\?")[0];
        List<Permission> list = permissionList.stream().filter(p -> realUri.equals(p.getPath())).collect(Collectors.toList());
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }
}
