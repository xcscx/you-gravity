package com.itegg.yougravitybackend.model.vo.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜谱-步骤关联添加请求参数
 * @author ITegg
 */
@Data
public class RecStepUpdateParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 菜谱id
     */
    private Long recipeId;

    /**
     * 步骤顺序
     */
    private Long stepNumber;

    /**
     * 步骤描述
     */
    private String description;

    /**
     * 动作类型
     */
    private String actionType;

}
