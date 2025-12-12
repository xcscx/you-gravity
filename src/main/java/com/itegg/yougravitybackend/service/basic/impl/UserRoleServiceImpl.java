package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.UserRoleMapper;
import com.itegg.yougravitybackend.model.entity.basic.UserRole;
import com.itegg.yougravitybackend.service.basic.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户角色Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {


}
