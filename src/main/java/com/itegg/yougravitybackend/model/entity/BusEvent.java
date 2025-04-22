package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 活动表
 * @TableName bus_event
 */
@TableName(value ="bus_event")
@Data
public class BusEvent {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @TableField("event_name")
    private String eventName;

    /**
     * 活动地点
     */
    @TableField("event_city")
    private String eventCity;

    /**
     * 开始时间
     */
    @TableField("event_start_time")
    private Date eventStartTime;

    /**
     * 结束时间
     */
    @TableField("event_end_time")
    private Date eventEndTime;

    /**
     * 活动背景图片
     */
    @TableField("event_background_image")
    private String eventBackgroundImage;

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