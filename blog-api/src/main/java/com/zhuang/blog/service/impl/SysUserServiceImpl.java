package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.SysUserDao;
import com.zhuang.blog.pojo.SysUser;
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
    public SysUser findById(Long id) {
        SysUser user = sysUserDao.findById(id);
        //防止为空,设置默认的nickname
        if (user == null) {
            user = new SysUser();
            user.setNickname("唐小壮");
        }
        return user;
    }
}
