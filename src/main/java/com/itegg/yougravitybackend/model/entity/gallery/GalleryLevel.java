package com.itegg.yougravitybackend.model.entity.gallery;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图库等级表 gallery_level
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="gallery_level")
@Data
public class GalleryLevel extends SuperModel {

    /**
     * 等级名称
     */
    @TableField("level_name")
    private String levelName;

    /**
     * 介绍
     */
    @TableField("introduction")
    private Long introduction;

    /**
     * 最大容量 单位 G
     */
    @TableField("max_capacity")
    private Long maxCapacity;

    /**
     * 最大大小 单位 千张
     */
    @TableField("max_image_count")
    private Long maxImageCount;

    /**
     * 创建人id
     */
    @TableField("create_by")
    private Long createBy;

}



