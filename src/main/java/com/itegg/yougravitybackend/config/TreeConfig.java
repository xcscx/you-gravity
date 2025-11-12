package com.itegg.yougravitybackend.config;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 树配置
 *
 * @author ITEgg
 */
@Data
public class TreeConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 默认属性配置对象
     */
    public static TreeConfig DEFAULT_CONFIG = new TreeConfig();

    /**
     * 属性名称配置字敦
     */
    private String idKey = "id";
    private String parentIdKey = "parentId";
    private String weightKey = "weight";
    private String nameKey = "name";
    private String childrenKey = "children";

}
