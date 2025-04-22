package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 地点表
 * @TableName bus_location
 */
@TableName(value ="bus_location")
@Data
public class BusLocation {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    private Long event_id;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地点名称
     */
    private String location_name;

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