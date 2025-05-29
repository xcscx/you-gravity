package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BusLocationWantGo implements Serializable {

    @TableId
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("location_id")
    private Long locationId;

    @TableField("create_time")
    private LocalDateTime createTime;
}
