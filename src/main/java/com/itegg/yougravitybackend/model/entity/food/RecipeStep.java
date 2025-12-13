package com.itegg.yougravitybackend.model.entity.food;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜谱步骤表 food_recipe_step
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="food_recipe_step")
@Data
public class RecipeStep extends SuperModel {

    /**
     * 菜谱ID
     */
    @TableField("recipe_id")
    private Long recipeId;

    /**
     * 步骤顺序（从1开始）
     */
    @TableField("step_number")
    private Integer stepNumber;

    /**
     * 步骤描述
     */
    @TableField("description")
    private String description;

    /**
     * 动作类型
     */
    @TableField("action_type")
    private String actionType;

}
