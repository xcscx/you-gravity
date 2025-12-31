package com.itegg.yougravitybackend.model.vo.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色 VO
 * @author ITegg
 */
@Data
public class UserRoleVO  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}
