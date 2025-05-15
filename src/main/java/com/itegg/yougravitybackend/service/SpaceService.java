package com.itegg.yougravitybackend.service;

import com.itegg.yougravitybackend.model.dto.space.SpaceAddRequest;
import com.itegg.yougravitybackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.User;

/**
* @author ITegg
* @description 针对表【image_space(图库空间表)】的数据库操作Service
* @createDate 2025-05-09 13:53:00
*/
public interface SpaceService extends IService<Space> {

    /**
     * 添加空间
     * @param request 添加请求
     * @param loginUser 登录用户
     * @return 空间id
     */
    long addSpace(SpaceAddRequest request, User loginUser);

    /**
     * 校验空间信息
     * @param space 空间信息
     * @param add 判断是否为创建
     */
    void validSpace(Space space, boolean add);

    /**
     * 依据空间级别填充空间信息
     * @param space 空间信息
     */
    void fillSpaceBySpaceLevel(Space space);

}
