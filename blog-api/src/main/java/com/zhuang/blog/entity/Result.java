package com.zhuang.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tangqingbo
 * @Date 2021/8/27 23:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态消息
     */
    private String message;

    /**
     * 结果数据
     */
    private Object data;

    /**
     * 成功方法
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    /**
     * 失败方法
     *
     * @param code
     * @param message
     * @return
     */
    public static Result fail(Integer code, String message) {
        return new Result(false, code, message, null);
    }
}
