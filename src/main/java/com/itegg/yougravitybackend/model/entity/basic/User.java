package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.*;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表 basic_user
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_user")
@Data
public class User extends superModel {

    /**
     * 用户名称
     */
    @TableField("name")
    private String name;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 账号密码
     */
    @TableField("password")
    private String password;

    /**
     * 密码盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 个性签名
     */
    @TableField("signature")
    private String signature;

    /**
     * 账号状态
     */
    @TableField("state")
    private Integer state;

}