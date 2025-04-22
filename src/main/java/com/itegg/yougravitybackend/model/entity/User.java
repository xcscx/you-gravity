package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String user_account;

    /**
     * 密文密码
     */
    private String user_password;

    /**
     * 用户昵称
     */
    private String user_name;

    /**
     * 用户头像
     */
    private String user_avatar;

    /**
     * 用户简介
     */
    private String user_profile;

    /**
     * 用户标签
     */
    private String tags;

    /**
     * 用户角色 user/admin
     */
    private String user_role;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 最后更新时间
     */
    private Date update_time;

    /**
     * 是否删除 0-否 1-是
     */
    @TableLogic
    private Integer remove_flag;
}