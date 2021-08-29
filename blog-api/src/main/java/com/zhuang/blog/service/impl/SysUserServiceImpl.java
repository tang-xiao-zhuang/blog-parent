package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.SysUserDao;
import com.zhuang.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author tangqingbo
 * @Date 2021/8/28 12:04
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据ID查询用户信息
     *
     * @param id 作者ID
     * @return
     */
    @Override
    public String findById(Long id) {
        return sysUserDao.findById(id);
    }
}
