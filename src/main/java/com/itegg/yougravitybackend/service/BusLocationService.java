package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationAddRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationQueryRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationUpdateRequest;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ITegg
* @description 针对表【bus_location(地点表)】的数据库操作Service
* @createDate 2025-04-21 15:05:10
*/
public interface BusLocationService extends IService<BusLocation> {

    /**
     * 添加地点
     * @param locationAddRequest 新增地点信息
     * @return 地点id
     */
    long addLocation(LocationAddRequest locationAddRequest);

    /**
     * 修改地点信息
     * @param locationUpdateRequest 修改请求参数
     * @return 修改结果
     */
    boolean updateLocation(LocationUpdateRequest locationUpdateRequest);

    /**
     * 获取地点列表
     * @param locationQueryRequest 请求条件
     * @return 查询地点列表
     */
    QueryWrapper<BusLocation> getLocationList(LocationQueryRequest locationQueryRequest);

}
