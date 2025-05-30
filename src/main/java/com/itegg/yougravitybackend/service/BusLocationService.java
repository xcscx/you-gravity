package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationAddRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationQueryRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationUpdateRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.WantGoQueryRequest;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.BusLocationVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

    /**
     * 用户想去地点
     * @param id 地点id
     * @param request 请求
     * @return 想去id
     */
    Long wantGo(Long id, HttpServletRequest request);

    /**
     * 用户取消想去
     * @param id 地点id
     * @param request 请求
     * @return 取消结果
     */
    Boolean notWantGo(Long id, HttpServletRequest request);

    /**
     * 获取用户想去地点列表
     * @param wantGoQueryRequest 搜索条件
     * @param request 请求
     * @return 用户想去地点列表
     */
    List<BusLocationVO> wantGoList(WantGoQueryRequest wantGoQueryRequest, HttpServletRequest request);

}
