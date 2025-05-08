package com.itegg.yougravitybackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 图片更新请求 - 用户更新
 */
@Data
public class PictureEditRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private List<String> tags;

}