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
    private String tag_name;

    /**
     * 父标签id
     */
    private Long parent_id;

    /**
     * 是否为父标签:0不是-1是
     */
    private Integer is_parent;

    /**
     * 创建人名称
     */
    private Long create_by;

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