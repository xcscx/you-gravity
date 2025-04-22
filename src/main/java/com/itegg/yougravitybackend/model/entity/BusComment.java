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
    private Long location_id;

    /**
     * 评论信息
     */
    private String message;

    /**
     * 点赞数量
     */
    private Integer like_count;

    /**
     * 回复的评论，默认为0
     */
    private Long parent_id;

    /**
     * 创建人
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