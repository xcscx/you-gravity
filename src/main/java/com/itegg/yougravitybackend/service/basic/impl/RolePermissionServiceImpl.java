package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.basic.RolePermissionMapper;
import com.itegg.yougravitybackend.model.entity.basic.RolePermission;
import com.itegg.yougravitybackend.model.vo.user.RolePermissionVO;
import com.itegg.yougravitybackend.service.basic.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {

    @Override
    public List<RolePermissionVO> getRolePermission(Long roleId) {
        if(roleId == null) {
            return null;
        }
        return this.baseMapper.getRolePermission(roleId);
    }

}
