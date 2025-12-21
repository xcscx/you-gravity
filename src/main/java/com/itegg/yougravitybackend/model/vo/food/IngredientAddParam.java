package com.itegg.yougravitybackend.model.vo.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 食材基本信息添加请求参数
 * @author ITegg
 */
@Data
public class IngredientAddParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 食材别名
     */
    private String alias;

    /**
     * 基础单位
     */
    private String unit;

    /**
     * 食材图片
     */
    private String imageUrl;

    /**
     * 食材描述
     */
    private String description;

    /**
     * 存储建议
     */
    private String storageTips;

}
