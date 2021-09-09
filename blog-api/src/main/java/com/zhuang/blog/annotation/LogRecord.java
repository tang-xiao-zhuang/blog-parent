package com.zhuang.blog.annotation;

import java.lang.annotation.*;

/**
 * @Author tangqingbo
 * @Date 2021/9/9 22:26
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {
    /**
     * 模块
     *
     * @return
     */
    String module() default "";

    /**
     * 操作信息
     *
     * @return
     */
    String operation() default "";
}
