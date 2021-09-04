package com.zhuang.blog.utils;

import com.zhuang.blog.pojo.SysUser;

/**
 * @Author tangqingbo
 * @Date 2021/9/4 09:51
 * @Version 1.0
 */
public class UserThreadLocalUtil {

    private static final ThreadLocal<SysUser> USER_THREAD_LOCAL = new ThreadLocal<>();

    public UserThreadLocalUtil() {

    }

    /**
     * 将用户信息放入threadLocal中
     *
     * @param sysUser
     */
    public static void put(SysUser sysUser) {
        USER_THREAD_LOCAL.set(sysUser);
    }

    /**
     * 从ThreadLocal中获取用户信息
     *
     * @return
     */
    public static SysUser get() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 将用户信息从ThreadLocal中移除
     */
    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }
}
