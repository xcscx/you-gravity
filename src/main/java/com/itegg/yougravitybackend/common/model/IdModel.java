package com.itegg.yougravitybackend.common.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class IdModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableLogic
    private Integer removeFlag;

}
