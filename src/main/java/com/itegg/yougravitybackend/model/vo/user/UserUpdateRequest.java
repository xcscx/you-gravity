package com.itegg.yougravitybackend.model.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新请求
 * @author ITegg
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 个性签名
     */
    private String signature;


}
