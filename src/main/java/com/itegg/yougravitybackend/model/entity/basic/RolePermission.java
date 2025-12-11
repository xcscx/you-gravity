package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限表 basic_role_permission
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_role_permission")
@Data
public class RolePermission extends SuperModel {

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;

}
