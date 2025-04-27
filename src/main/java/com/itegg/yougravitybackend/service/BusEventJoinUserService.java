package com.itegg.yougravitybackend.service;

import com.itegg.yougravitybackend.model.dto.busEvent.EventUserJoinRequest;
import com.itegg.yougravitybackend.model.entity.BusEventJoinUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ITegg
* @description 针对表【bus_event_join_user(活动参与表)】的数据库操作Service
* @createDate 2025-04-22 11:58:36
*/
public interface BusEventJoinUserService extends IService<BusEventJoinUser> {

    /**
     * 用户参加活动
     * @param request 请求参数
     * @return 关联id
     */
    long joinEvent(EventUserJoinRequest request);

    /**
     * 用户退出活动
     * @param request 请求参数
     * @return 是否退出成功
     */
    boolean quitEvent(EventUserJoinRequest request);

}
