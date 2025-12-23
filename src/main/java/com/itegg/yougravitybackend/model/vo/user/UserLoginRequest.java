package com.itegg.yougravitybackend.model.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 * @author ITegg
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 6547841231584616L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

}