package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限表 basic_permission
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_permission")
@Data
public class Permission extends SuperModel {

    /**
     * 权限编码
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 权限类型：按钮，菜单
     */
    @TableField("permission_type")
    private String permissionType;

    /**
     * 链接地址
     */
    @TableField("href")
    private String href;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 深度，用于记录树结构层级关系
     */
    @TableField("depth")
    private Integer depth;

    /**
     * 路径
     */
    @TableField("path")
    private String path;

    /**
     * 排序字段
     */
    @TableField("sorting")
    private Integer sorting;

}
