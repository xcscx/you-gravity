package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 用户标签表
 * @TableName user_tag
 */
@TableName(value ="user_tag")
@Data
public class UserTag extends superModel {

    /**
     * 标签名
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * 父标签id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否为父标签:0不是-1是
     */
    @TableField("is_parent")
    private Integer isParent;

    /**
     * 创建人名称
     */
    @TableField("create_by")
    private Long createBy;
}