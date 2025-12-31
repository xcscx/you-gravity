package com.itegg.yougravitybackend.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.basic.UserRole;
import com.itegg.yougravitybackend.model.vo.user.UserRoleVO;

import java.util.List;

/**
 * 用户角色 Service层
 * @author ITegg
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 依据用户id获取用户全部角色
     */
    List<UserRoleVO> getUserRole(Long userId);

}
