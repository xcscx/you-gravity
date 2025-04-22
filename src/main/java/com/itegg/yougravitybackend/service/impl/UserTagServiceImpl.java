package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.model.entity.UserTag;
import com.itegg.yougravitybackend.service.UserTagService;
import com.itegg.yougravitybackend.mapper.UserTagMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【user_tag(用户标签表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:16
*/
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag>
    implements UserTagService{

}




