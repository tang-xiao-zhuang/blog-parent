package com.zhuang.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author tangqingbo
 * @Date 2021/9/11 22:23
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zhuang.admin.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
