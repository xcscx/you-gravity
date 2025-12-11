package com.itegg.yougravitybackend.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuperModel extends IdModel {

    @Serial
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
