package com.zhuang.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author tangqingbo
 * @Date 2021/8/25 22:11
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zhuang.blog.dao")
@EnableAsync
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
