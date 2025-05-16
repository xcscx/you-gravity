package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 图库空间表
 * @TableName space
 */
@TableName(value ="space")
@Data
public class Space extends superModel {

    /**
     * 空间名称
     */
    @TableField("space_name")
    private String spaceName;

    /**
     * 空间描述
     */
    @TableField("space_desc")
    private String spaceDesc;

    /**
     * 空间等级 0-普通 1-专业 2-旗舰
     */
    @TableField("space_level")
    private Integer spaceLevel;

    /**
     * 最大容量
     */
    @TableField("max_size")
    private Long maxSize;

    /**
     * 最大数量
     */
    @TableField("max_count")
    private Long maxCount;

    /**
     * 已使用容量
     */
    @TableField("total_size")
    private Long totalSize;

    /**
     * 已使用数量
     */
    @TableField("total_count")
    private Long totalCount;

    /**
     * 创建人id
     */
    @TableField("create_by")
    private Long createBy;

}