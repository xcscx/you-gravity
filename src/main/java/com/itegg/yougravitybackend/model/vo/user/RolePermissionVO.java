package com.itegg.yougravitybackend.model.vo.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色 VO
 * @author ITegg
 */
@Data
public class RolePermissionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限类型
     */
    private Integer permissionType;

}
