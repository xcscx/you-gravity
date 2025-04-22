package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 用户标签表
 * @TableName user_tag
 */
@TableName(value ="user_tag")
@Data
public class UserTag {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否删除 0-否 1-是
     */
    @TableLogic
    @TableField("remove_flag")
    private Integer removeFlag;
}