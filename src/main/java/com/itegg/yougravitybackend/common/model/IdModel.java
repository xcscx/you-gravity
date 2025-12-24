package com.itegg.yougravitybackend.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共id模型
 * @author ITegg
 */
@Data
public class IdModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableLogic
    @TableField(value = "remove_flag")
    private Integer removeFlag;

}
