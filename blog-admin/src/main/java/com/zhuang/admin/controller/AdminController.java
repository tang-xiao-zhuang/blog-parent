package com.zhuang.admin.controller;

import com.zhuang.admin.entity.PageParam;
import com.zhuang.admin.entity.Result;
import com.zhuang.admin.pojo.Permission;
import com.zhuang.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 22:54
 * @Version 1.0
 */
@RestController
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询权限列表
     *
     * @param pageParam
     * @return
     */
    @PostMapping("/admin/permission/permissionList")
    public Result permissionList(@RequestBody PageParam pageParam) {
        return permissionService.permissionList(pageParam);
    }

    /**
     * 增加权限
     *
     * @param permission
     * @return
     */
    @PostMapping("/admin/permission/add")
    public Result addPermission(@RequestBody Permission permission) {
        return permissionService.addPermission(permission);
    }

    /**
     * 根据ID删除权限
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/permission/delete/{id}")
    public Result deleteById(@PathVariable(value = "id") Long id) {
        return permissionService.deleteById(id);
    }

    /**
     * 修改权限配置信息
     *
     * @param permission
     * @return
     */
    @PostMapping("/admin/permission/update")
    public Result updatePermission(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);
    }
}
