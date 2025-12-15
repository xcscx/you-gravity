package com.itegg.yougravitybackend.model.entity.food;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 食品种类表
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="ingredient_category")
@Data
public class IngredientCategory extends SuperModel {

    /**
     * 父分类Id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 分类图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 分类描述
     */
    @TableField("description")
    private String description;

    /**
     * 分类排序序号
     */
    @TableField("sort_order")
    private Integer sortOrder;

    /**
     * 分类层级
     */
    @TableField("level")
    private Integer level;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}
