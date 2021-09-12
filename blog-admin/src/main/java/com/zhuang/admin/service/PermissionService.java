package com.zhuang.admin.service;

import com.zhuang.admin.entity.PageParam;
import com.zhuang.admin.entity.Result;
import com.zhuang.admin.pojo.Permission;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 22:57
 * @Version 1.0
 */
public interface PermissionService {

    /**
     * 查询权限列表
     *
     * @param pageParam
     * @return
     */
    Result permissionList(PageParam pageParam);

    /**
     * 增加权限
     *
     * @param permission
     * @return
     */
    Result addPermission(Permission permission);

    /**
     * 根据ID删除权限
     *
     * @param id
     * @return
     */
    Result deleteById(Long id);

    /**
     * 修改权限配置信息
     *
     * @param permission
     * @return
     */
    Result updatePermission(Permission permission);
}
