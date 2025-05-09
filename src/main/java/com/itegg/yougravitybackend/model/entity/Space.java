package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 图库空间表
 * @TableName image_space
 */
@TableName(value ="image_space")
@Data
public class Space extends superModel {

    /**
     * 空间名称
     */
    private String space_name;

    /**
     * 空间描述
     */
    private String space_desc;

    /**
     * 空间等级 0-普通 1-专业 2-旗舰
     */
    private Integer space_level;

    /**
     * 最大容量
     */
    private Long max_size;

    /**
     * 最大数量
     */
    private Long max_count;

    /**
     * 已使用容量
     */
    private Long total_size;

    /**
     * 已使用数量
     */
    private Long total_count;

    /**
     * 创建人id
     */
    private Long create_by;

}