package com.itegg.yougravitybackend.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.basic.RolePermission;
import com.itegg.yougravitybackend.model.vo.user.RolePermissionVO;

import java.util.List;

/**
 * 角色权限 Service层
 * @author ITegg
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 依据角色id获取全部权限
     */
    List<RolePermissionVO> getRolePermission(Long roleId);

}
