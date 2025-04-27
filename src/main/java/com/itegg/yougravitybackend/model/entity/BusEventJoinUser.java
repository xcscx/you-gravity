package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;


import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 活动参与表
 * @TableName bus_event_join_user
 */
@TableName(value ="bus_event_join_user")
@Data
public class BusEventJoinUser extends superModel{

    /**
     * 活动id
     */
    @TableField("event_id")
    private Long eventId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

}