package com.itegg.yougravitybackend.model.entity.food;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜谱表 food_recipe
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="food_recipe")
@Data
public class FoodRecipe extends SuperModel {

    /**
     * 创建人ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 菜谱名称
     */
    @TableField("title")
    private String title;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 封面
     */
    @TableField("url")
    private String url;

    /**
     * 是否公开 0-否 1-是
     */
    @TableField("is_public")
    private Integer isPublic;

}
