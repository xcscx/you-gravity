package com.itegg.yougravitybackend.model.vo.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜谱步骤 VO对象
 * @author ITegg
 */
@Data
public class RecStepVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜谱ID
     */
    private Long recipeId;

    /**
     * 步骤顺序（从1开始）
     */
    private Integer stepNumber;

    /**
     * 步骤描述
     */
    private String description;

    /**
     * 动作类型
     */
    private String actionType;

}
