package com.itegg.yougravitybackend.model.dto.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户注册请求参数
 * @author ITegg
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

}
