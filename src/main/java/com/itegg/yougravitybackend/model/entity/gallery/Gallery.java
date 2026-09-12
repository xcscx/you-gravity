package com.itegg.yougravitybackend.model.entity.gallery;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图库表 gallery
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="gallery")
@Data
public class Gallery extends SuperModel {

    /**
     * 图库名称
     */
    @TableField("gallery_name")
    private String galleryName;

    /**
     * 图库描述
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 等级id
     */
    @TableField("level_id")
    private Long levelId;

    /**
     * 已用大小
     */
    @TableField("used_capacity")
    private int usedCapacity;

    /**
     * 已用数量
     */
    @TableField("used_image_count")
    private int usedImageCount;

    /**
     * 创建人id
     */
    @TableField("create_by")
    private Long createBy;

}
