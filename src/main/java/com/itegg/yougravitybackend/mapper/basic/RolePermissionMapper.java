package com.itegg.yougravitybackend.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itegg.yougravitybackend.model.entity.basic.RolePermission;
import com.itegg.yougravitybackend.model.vo.user.RolePermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限关联 mapper类
 * @author ITegg
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 获取角色权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<RolePermissionVO> getRolePermission(@Param("roleId") Long roleId);

}
