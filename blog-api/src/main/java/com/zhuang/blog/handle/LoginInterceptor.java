package com.zhuang.blog.handle;

import com.google.gson.Gson;
import com.zhuang.blog.entity.Result;
import com.zhuang.blog.pojo.SysUser;
import com.zhuang.blog.service.LoginService;
import com.zhuang.blog.utils.UserThreadLocalUtil;
import com.zhuang.blog.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author tangqingbo
 * @Date 2021/9/2 22:50
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    /**
     * 在方法之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求的方法是否是controller方法
        if (!(handler instanceof HandlerMethod)) {
            //非controller方法直接放行
            return true;
        }
        //判断token是否为空,为空: 未登录
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new Gson().toJson(result));
            return false;
        }
        //如果token不为空,校验token
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new Gson().toJson(result));
            return false;
        }
        //校验成功,将用户信息放入ThreadLocal中
        UserThreadLocalUtil.put(sysUser);
        return true;
    }

    /**
     * 执行后移除ThreadLocal中保存的用户信息,防止内存泄露
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalUtil.remove();
    }
}
