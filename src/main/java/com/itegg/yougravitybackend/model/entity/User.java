package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User extends superModel {

    /**
     * 用户账号
     */
    @TableField("user_account")
    private String userAccount;

    /**
     * 密文密码
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户头像
     */
    @TableField("user_avatar")
    private String userAvatar;

    /**
     * 用户简介
     */
    @TableField("user_profile")
    private String userProfile;

    /**
     * 用户标签
     */
    @TableField("tags")
    private String tags;

    /**
     * 用户角色 user/admin
     */
    @TableField("user_role")
    private String userRole;

}