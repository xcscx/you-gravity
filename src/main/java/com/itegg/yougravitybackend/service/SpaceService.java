package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.model.dto.space.SpaceAddRequest;
import com.itegg.yougravitybackend.model.dto.space.SpaceQueryRequest;
import com.itegg.yougravitybackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.vo.SpaceVO;

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

    /***
     * 获取空间查询条件
     * @param request 请求
     * @return 列表参数
     */
    QueryWrapper<Space> getSpaceQueryWrapper(SpaceQueryRequest request);

    /**
     * 获取空间vo列表
     * @param spacePage 空间分页
     * @return 空间vo分页
     */
    Page<SpaceVO> getSpaceVOPage(Page<Space> spacePage);

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
