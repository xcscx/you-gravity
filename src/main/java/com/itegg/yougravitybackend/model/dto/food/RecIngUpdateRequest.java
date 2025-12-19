package com.itegg.yougravitybackend.model.dto.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 食材-菜谱关联添加请求参数
 * @author ITegg
 */
@Data
public class RecIngUpdateRequest implements Serializable {

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
     * 食材id
     */
    private Long ingredientId;

    /**
     * 食材名称
     */
    private String ingredientName;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 用量数值
     */
    private String amount;

    /**
     * 用量单位
     */
    private String unit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预处理说明
     */
    private String preparation;

}
