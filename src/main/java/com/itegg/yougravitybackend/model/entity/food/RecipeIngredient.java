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
@TableName(value ="recipe_ingredient")
@Data
public class RecipeIngredient extends SuperModel {

    /**
     * 菜谱id
     */
    @TableField("recipe_id")
    private Long recipeId;

    /**
     * 食材id
     */
    @TableField("ingredient_id")
    private Long ingredientId;

    /**
     * 食材名称
     */
    @TableField("ingredient_name")
    private String ingredientName;

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 用量数值
     */
    @TableField("amount")
    private String amount;

    /**
     * 用量单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 预处理说明（洗净，泡发，腌制）
     */
    @TableField("preparation")
    private String preparation;

}
