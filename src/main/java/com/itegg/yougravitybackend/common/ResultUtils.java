package com.itegg.yougravitybackend.common;

import com.itegg.yougravitybackend.exception.ErrorCode;

/**
 * 全局响应返回类
 * @author ITegg
 */
public class ResultUtils {

    /**
     * 成功请求, 返回数据体
     * @param data 返回参数
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(200, data, "ok");
    }

    /**
     * 错误请求，返回错误码信息
     * @param errorCode 错误码
     */
    public static Result<?> error(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    /**
     * 错误请求，返回错误码信息
     * @param errorCode 错误码
     */
    public static Result<?> error(ErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), null, message);
    }

    /**
     * 错误请求，返回错误码信息
     * @param errorCode 错误码
     */
    public static Result<?> error(int errorCode, String message) {
        return new Result<>(errorCode, null, message);
    }

}
