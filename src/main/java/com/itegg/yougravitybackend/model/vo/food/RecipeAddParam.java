package com.itegg.yougravitybackend.model.vo.food;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 菜谱添加请求
 * @author ITegg
 */
@Data
public class RecipeAddParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜谱id
     */
    private Long id;

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
     * 关联食材列表
     */
    private List<RecIngAddParam> recIngList;

    /**
     * 关联步骤列表
     */

}
