package com.zhuang.blog.aspect;

import com.zhuang.blog.annotation.LogRecord;
import com.zhuang.blog.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author tangqingbo
 * @Date 2021/9/9 22:29
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class LogRecordAspect {

    /**
     * 记录切入点
     */
    @Pointcut("@annotation(com.zhuang.blog.annotation.LogRecord)")
    public void pointcut() {

    }

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        //执行方法
        Object res = pjp.proceed();
        long end = System.currentTimeMillis();
        //执行耗时
        long useTime = end - start;
        recordLog(pjp, useTime);
        return res;
    }

    /**
     * 记录操作
     */
    private void recordLog(ProceedingJoinPoint pjp, long useTime) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        //获取方法上的注解
        LogRecord logRecord = method.getAnnotation(LogRecord.class);
        log.info("=====================log start=====================");
        log.info("module is {}", logRecord.module());
        log.info("operation is {}", logRecord.operation());

        //请求方法的信息
        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method is {}", className + "." + methodName);

        //请求参数
        Object[] args = pjp.getArgs();
        log.info("request params {} ", Arrays.toString(args));

        //获取请求IP地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("request ip {} ", IpUtils.getIpAddr(request));

        //执行耗时
        log.info("request use time: {} ms", useTime);
        log.info("=====================log end=====================");
    }
}
