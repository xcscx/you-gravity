package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.basic.PermissionMapper;
import com.itegg.yougravitybackend.model.entity.basic.Permission;
import com.itegg.yougravitybackend.service.basic.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 权限Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {


}
