package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itegg.yougravitybackend.model.dto.busEvent.EventAddRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventJoinQuertRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventQueryRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventUserJoinRequest;
import com.itegg.yougravitybackend.model.entity.BusEvent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.BusEventVO;

import java.util.List;


/**
* @author ITegg
* @description 针对表【bus_event(活动表)】的数据库操作Service
* @createDate 2025-04-21 15:05:08
*/
public interface BusEventService extends IService<BusEvent> {

    /**
     * 添加活动
     * @param eventAddRequest 新增用户参数
     * @return 活动id
     */
    long addBusEvent(EventAddRequest eventAddRequest);

    /**
     * 分页查询活动列表
     * @Param eventQueryRequest 分页查询参数
     * @return 符合条件的活动列表
     */
    QueryWrapper<BusEvent> getEventList(EventQueryRequest eventQueryRequest);

    /**
     * 当前用户已参加的活动列表
     * @param eventJoinQuertRequest 搜索参数
     * @return 符合条件的分页列表
     */
    List<BusEventVO> getEventByUserJoin(EventJoinQuertRequest eventJoinQuertRequest);

    /**
     * 当前用户未参与的活动列表
     * @param eventJoinQuertRequest 搜索参数
     * @return 符合条件的分页列表
     */
    List<BusEventVO> getEventByUserNotJoin(EventJoinQuertRequest eventJoinQuertRequest);

    /**
     * 活动报名
     * @param request 请求参数
     * @return 是否报名成功
     */
    long joinEvent(EventUserJoinRequest request);

    /**
     * 活动退出
     * @param request 请求参数
     * @return 是否退出成功
     */
    boolean quitEvent(EventUserJoinRequest request);

}
