package com.itegg.yougravitybackend.exception;

import lombok.Getter;

/**
 * 自定义错误码
 * @author ITegg
 */
@Getter
public enum ErrorCode {

    // 成功
    SECCESS(200, "ok"),
    // 客户端异常
    PARAMS_ERROR(400, "请求参数错误"),
    NOT_LOGIN_ERROR(401, "未登录"),
    NO_AUTH_ERROR(401, "权限不足"),
    FORBIDDEN_ERROR(403, "禁止访问"),
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    REQUEST_TYPE_ERROR(405, "请求方法错误"),
    // 服务端异常
    SYSTEM_ERROR(500,"系统内部异常"),
    OPERATION_ERROR(500, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 消息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
