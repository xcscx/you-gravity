package com.itegg.yougravitybackend.model.entity;

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
    private String spaceName;

    /**
     * 空间描述
     */
    private String spaceDesc;

    /**
     * 空间等级 0-普通 1-专业 2-旗舰
     */
    private Integer spaceLevel;

    /**
     * 最大容量
     */
    private Long maxSize;

    /**
     * 最大数量
     */
    private Long maxCount;

    /**
     * 已使用容量
     */
    private Long totalSize;

    /**
     * 已使用数量
     */
    private Long totalCount;

    /**
     * 创建人id
     */
    private Long createBy;

}