package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 地点表
 * @TableName bus_location
 */
@TableName(value ="bus_location")
@Data
public class BusLocation extends superModel {

    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 经度
     */
    @TableField("longitude")
    private String longitude;

    /**
     * 纬度
     */
    @TableField("latitude")
    private String latitude;

    /**
     * 地点名称
     */
    @TableField("location_name")
    private String locationName;

    /**
     * 想去人数
     */
    @TableField("want_go_count")
    private Integer wantGoCount;

}