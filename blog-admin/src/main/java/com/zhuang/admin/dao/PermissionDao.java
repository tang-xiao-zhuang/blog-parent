package com.zhuang.admin.dao;

import com.zhuang.admin.entity.PageParam;
import com.zhuang.admin.pojo.Permission;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 23:01
 * @Version 1.0
 */
public interface PermissionDao {

    /**
     * 查询权限列表
     *
     * @param start
     * @param end
     * @param queryString
     * @return
     */
    List<Permission> permissionList(Integer start, Integer end, String queryString);

    /**
     * 查询权限列表总数
     *
     * @return
     */
    Long total();

    /**
     * 增加权限
     *
     * @param permission
     */
    void addPermission(Permission permission);

    /**
     * 根据ID删除权限
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改权限配置信息
     *
     * @param permission
     */
    void updatePermission(Permission permission);
}
