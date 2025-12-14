package com.itegg.yougravitybackend.model.dto.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜谱添加请求
 * @author ITegg
 */
@Data
public class RecipeAddRequest implements Serializable {

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

}
