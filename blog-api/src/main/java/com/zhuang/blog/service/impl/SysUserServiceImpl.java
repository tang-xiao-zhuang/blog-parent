package com.zhuang.blog.service.impl;

import com.zhuang.blog.dao.SysUserDao;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.SysUser;
import com.zhuang.blog.service.LoginService;
import com.zhuang.blog.service.SysUserService;
import com.zhuang.blog.vo.ErrorCode;
import com.zhuang.blog.vo.LoginUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private LoginService loginService;

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

    /**
     * 根据用户名密码获取用户信息
     *
     * @param account  账户名
     * @param password 密码
     * @return
     */
    @Override
    public SysUser findUserByAccountAndPassword(String account, String password) {
        return sysUserDao.findUserByAccountAndPassword(account, password);
    }

    /**
     * 根据token获取用户信息
     *
     * @param token token信息
     * @return
     */
    @Override
    public Result findUserByToken(String token) {
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser, loginUserVo);
        return Result.success(loginUserVo);
    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }
}
