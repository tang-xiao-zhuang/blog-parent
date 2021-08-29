package com.zhuang.blog.config;

import com.zhuang.blog.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 11:07
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = "com.zhuang.blog")
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandle(Exception e) {
        e.printStackTrace();
        return Result.fail(-999, "系统异常,请联系管理员!");
    }
}
