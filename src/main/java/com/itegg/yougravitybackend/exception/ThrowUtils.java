package com.itegg.yougravitybackend.exception;

/**
 * 异常处理工具类
 * @author xtx
 */
public class ThrowUtils {

    /**
     * 依据判断抛出异常 - 断言
     * @param condition 判断依据
     * @param runtimeException 异常类型
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if(condition) {
            throw runtimeException;
        }
    }

    /**
     * 依据错误码进行异常抛出
     * @param condition 判断依据
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 依据错误码和自定义信息进行异常抛出
     * @param condition 判断依据
     * @param errorCode 错误码
     * @param message 自定义消息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }

}
