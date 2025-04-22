package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName bus_comment
 */
@TableName(value ="bus_comment")
@Data
public class BusComment {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 地点id
     */
    @TableField("location_id")
    private Long locationId;

    /**
     * 评论信息
     */
    @TableField("message")
    private String message;

    /**
     * 点赞数量
     */
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 回复的评论，默认为0
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 创建人
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