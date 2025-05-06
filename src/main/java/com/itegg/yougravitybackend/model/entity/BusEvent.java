package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 活动表
 * @TableName bus_event
 */
@TableName(value ="bus_event")
@Data
public class BusEvent extends superModel {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventStartTime;

    /**
     * 结束时间
     */
    @TableField("event_end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventEndTime;

    /**
     * 活动背景图片
     */
    @TableField("event_background_image")
    private String eventBackgroundImage;

}