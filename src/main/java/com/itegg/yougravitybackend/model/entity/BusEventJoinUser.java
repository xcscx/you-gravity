package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 活动参与表
 * @TableName bus_event_join_user
 */
@TableName(value ="bus_event_join_user")
@Data
public class BusEventJoinUser {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
    @TableField("remove_flag")
    private Integer removeFlag;
}