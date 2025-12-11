package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表 basic_role
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_role")
@Data
public class Role extends SuperModel {

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("description")
    private String description;

    /**
     * 有效状态
     */
    @TableField("state")
    private Integer state;

}
