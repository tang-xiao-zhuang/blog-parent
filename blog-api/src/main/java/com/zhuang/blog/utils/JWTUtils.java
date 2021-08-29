package com.zhuang.blog.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tangqingbo
 * @Date 2021/8/29 22:14
 * @Version 1.0
 */
public class JWTUtils {
    /**
     * 秘钥
     */
    private static final String JWT_TOKEN = "123456Mszlu!@#$$";

    /**
     * 创建token
     *
     * @param userId
     * @return
     */
    public static String createToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                // 签发算法，秘钥为jwtToken
                .signWith(SignatureAlgorithm.HS256, JWT_TOKEN)
                // body数据，要唯一，自行设置
                .setClaims(claims)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 一天的有效时间
                .setExpiration(new Date(System.currentTimeMillis() + 24L * 60 * 60 * 1000));
        return jwtBuilder.compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Map<String, Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(JWT_TOKEN).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
