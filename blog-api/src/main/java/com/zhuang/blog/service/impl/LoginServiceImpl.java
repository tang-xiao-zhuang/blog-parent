package com.zhuang.blog.service.impl;

import com.google.gson.Gson;
import com.zhuang.blog.entity.LoginParam;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.SysUser;
import com.zhuang.blog.service.LoginService;
import com.zhuang.blog.service.SysUserService;
import com.zhuang.blog.utils.JWTUtils;
import com.zhuang.blog.vo.ErrorCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 22:20
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 加密盐
     */
    private static final String SLAT = "mszlu!@#";

    /**
     * 登录操作
     *
     * @param loginParam 登录参数信息
     * @return
     */
    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        //检验用户名密码参数
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //加密password,与数据库进行对应
        password = DigestUtils.md5Hex(password + SLAT);
        //查表校验
        SysUser sysUser = sysUserService.findUserByAccountAndPassword(account, password);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //生成token
        String token = JWTUtils.createToken(sysUser.getId());
        //将用户信息存入Redis 设置过期时间为1天
        redisTemplate.opsForValue().set("TOKEN_" + token, new Gson().toJson(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @Override
    public SysUser checkToken(String token) {
        //检查token信息
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        return new Gson().fromJson(userJson, SysUser.class);
    }

    /**
     * 注册
     *
     * @param loginParam
     * @return
     */
    @Override
    @Transactional
    public Result register(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        //判断参数信息
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //查询账户是否被使用
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXISTS.getCode(), ErrorCode.ACCOUNT_EXISTS.getMsg());
        }
        //注册用户
        SysUser user = new SysUser();
        user.setNickname(nickname);
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password + SLAT));
        user.setCreateDate(System.currentTimeMillis());
        user.setLastLogin(System.currentTimeMillis());
        user.setAvatar("/static/img/logo.b3a48c0.png");
        user.setAdmin(1);
        user.setDeleted(0);
        user.setSalt("");
        user.setStatus("");
        user.setEmail("");
        sysUserService.save(user);

        //生成token
        String token = JWTUtils.createToken(user.getId());
        //将用户信息存入Redis 设置过期时间为1天
        redisTemplate.opsForValue().set("TOKEN_" + token, new Gson().toJson(user), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
