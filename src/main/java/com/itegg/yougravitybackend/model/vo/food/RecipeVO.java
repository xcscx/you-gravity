package com.itegg.yougravitybackend.model.vo.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜谱 VO对象
 * @author ITegg
 */
@Data
public class RecipeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建人ID
     */
    private Long userId;

    /**
     * 菜谱名称
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 封面
     */
    private String url;

    /**
     * 是否公开 0-否 1-是
     */
    private Integer isPublic;

    /**
     * 状态
     */
    private String status;

    /**
     * 步骤列表
     */
    private List<RecStepVO> stepList;

    /**
     * 食材列表
     */
    private List<IngredientVO> ingredientList;

}
