package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.service.UserService;
import com.itegg.yougravitybackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:13
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




