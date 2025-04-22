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
    private String event_name;

    /**
     * 活动地点
     */
    private String event_city;

    /**
     * 开始时间
     */
    private Date event_start_time;

    /**
     * 结束时间
     */
    private Date event_end_time;

    /**
     * 活动背景图片
     */
    private String event_background_image;

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