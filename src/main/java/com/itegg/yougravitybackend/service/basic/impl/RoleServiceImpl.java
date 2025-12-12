package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.RoleMapper;
import com.itegg.yougravitybackend.model.entity.basic.Role;
import com.itegg.yougravitybackend.service.basic.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {


}
