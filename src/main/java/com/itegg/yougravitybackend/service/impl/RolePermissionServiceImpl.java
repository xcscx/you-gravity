package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.RolePermissionMapper;
import com.itegg.yougravitybackend.model.entity.basic.RolePermission;
import com.itegg.yougravitybackend.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色权限Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {

}
