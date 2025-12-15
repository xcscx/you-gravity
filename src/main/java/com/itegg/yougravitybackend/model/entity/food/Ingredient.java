package com.itegg.yougravitybackend.model.entity.food;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 食材基本信息表
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="ingredient")
@Data
public class Ingredient extends SuperModel {

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 食材名称
     */
    @TableField("name")
    private String name;

    /**
     * 食材别名
     */
    @TableField("alias")
    private String alias;

    /**
     * 基础单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 食材图片
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 食材描述
     */
    @TableField("description")
    private String description;

    /**
     * 存储建议
     */
    @TableField("storage_tips")
    private String storageTips;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}
