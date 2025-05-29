package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.itegg.yougravitybackend.common.model.IdModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BusLocationCheckIn extends IdModel {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Long userId;

    @TableField("location_id")
    private Long locationId;

    @TableField("message")
    private String message;

    @TableField("create_time")
    private LocalDateTime createTime;

}
