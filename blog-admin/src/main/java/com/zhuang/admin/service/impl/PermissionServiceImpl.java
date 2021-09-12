package com.zhuang.admin.service.impl;

import com.zhuang.admin.dao.PermissionDao;
import com.zhuang.admin.entity.PageParam;
import com.zhuang.admin.entity.PageResult;
import com.zhuang.admin.entity.Result;
import com.zhuang.admin.pojo.Permission;
import com.zhuang.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 22:57
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询权限列表
     *
     * @param pageParam
     * @return
     */
    @Override
    public Result permissionList(PageParam pageParam) {
        Integer currentPage = pageParam.getCurrentPage();
        Integer pageSize = pageParam.getPageSize();
        List<Permission> permissionList = permissionDao.permissionList((currentPage - 1) * pageSize, pageSize, pageParam.getQueryString());
        Long total = permissionDao.total();
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionList);
        pageResult.setTotal(total);
        return Result.success(pageResult);
    }

    /**
     * 增加权限
     *
     * @param permission
     * @return
     */
    @Override
    @Transactional
    public Result addPermission(Permission permission) {
        permissionDao.addPermission(permission);
        return Result.success(null);
    }

    /**
     * 根据ID删除权限
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result deleteById(Long id) {
        permissionDao.deleteById(id);
        return Result.success(null);
    }

    /**
     * 修改权限配置信息
     *
     * @param permission
     * @return
     */
    @Override
    public Result updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
        return Result.success(null);
    }
}
