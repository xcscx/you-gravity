package com.itegg.yougravitybackend.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itegg.yougravitybackend.model.entity.basic.UserRole;
import com.itegg.yougravitybackend.model.vo.user.UserRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关联 mapper类
 * @author ITegg
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 获取用户所属全部角色
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<UserRoleVO> getUserRole(@Param("userId") Long userId);

}
