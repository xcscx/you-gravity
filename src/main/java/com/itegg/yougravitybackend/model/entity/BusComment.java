package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;


import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 评论表
 * @TableName bus_comment
 */
@TableName(value ="bus_comment")
@Data
public class BusComment extends superModel {

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

}