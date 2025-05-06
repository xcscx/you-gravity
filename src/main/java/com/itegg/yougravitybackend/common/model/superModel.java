package com.itegg.yougravitybackend.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class superModel extends IdModel {

    private static final long serialVersionUID = 1L;

    @TableField(
            value = "create_time"
    )
    private LocalDateTime createTime;

    @TableField(
            value = "update_time"
    )
    private LocalDateTime updateTime;


}
