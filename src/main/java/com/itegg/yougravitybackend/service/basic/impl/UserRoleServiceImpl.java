package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.basic.UserRoleMapper;
import com.itegg.yougravitybackend.model.entity.basic.UserRole;
import com.itegg.yougravitybackend.model.vo.user.UserRoleVO;
import com.itegg.yougravitybackend.service.basic.RoleService;
import com.itegg.yougravitybackend.service.basic.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

    @Override
    public List<UserRoleVO> getUserRole(Long userId) {
        if (userId == null) {
            return null;
        }
        return this.baseMapper.getUserRole(userId);
    }

}
