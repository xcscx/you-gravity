package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.busEvent.EventAddRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventJoinQuertRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventQueryRequest;
import com.itegg.yougravitybackend.model.dto.busEvent.EventUserJoinRequest;
import com.itegg.yougravitybackend.model.entity.BusEvent;
import com.itegg.yougravitybackend.model.vo.BusEventVO;
import com.itegg.yougravitybackend.service.BusEventJoinUserService;
import com.itegg.yougravitybackend.service.BusEventService;
import com.itegg.yougravitybackend.mapper.BusEventMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
* @author ITegg
* @description 针对表【bus_event(活动表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:08
*/
@Service
public class BusEventServiceImpl extends ServiceImpl<BusEventMapper, BusEvent>
    implements BusEventService{

    @Resource
    private BusEventJoinUserService busEventJoinUserService;

    @Override
    public long addBusEvent(EventAddRequest req) {
        // 校验数据是否合规
        if(StrUtil.hasBlank(req.getEventName(), req.getEventCity(), req.getEventStartTime().toString(), req.getEventEndTime().toString(), req.getEventEndTime().toString())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "活动信息不完整");
        }
        if(req.getEventStartTime().isAfter(req.getEventEndTime())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "活动开始时间不能晚于活动结束时间");
        }
        // 校验数据是否重复
        QueryWrapper<BusEvent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_name", req.getEventName());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "活动名称重复");
        }
        // 添加活动
        BusEvent event = new BusEvent();
        BeanUtils.copyProperties(req, event);
        boolean saveResult = this.save(event);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常，活动添加失败");
        }
        return event.getId();
    }

    @Override
    public QueryWrapper<BusEvent> getEventList(EventQueryRequest eventQueryRequest) {
        QueryWrapper<BusEvent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(eventQueryRequest.getId()), "id", eventQueryRequest.getId());
        queryWrapper.eq(StrUtil.isNotBlank(eventQueryRequest.getEventName()), "event_name", eventQueryRequest.getEventName());
        queryWrapper.eq(StrUtil.isNotBlank(eventQueryRequest.getEventCity()), "event_city", eventQueryRequest.getEventCity());
        queryWrapper.ge(ObjectUtil.isNotNull(eventQueryRequest.getEventStartTime()), "event_start_time", eventQueryRequest.getEventStartTime());
        queryWrapper.le(ObjectUtil.isNotNull(eventQueryRequest.getEventEndTime()), "event_end_time", eventQueryRequest.getEventEndTime());
        queryWrapper.orderBy(StrUtil.isNotEmpty(eventQueryRequest.getSortOrder()), eventQueryRequest.getSortOrder().equals("asc"), eventQueryRequest.getSortField());
        return queryWrapper;
    }

    @Override
    public List<BusEventVO> getEventByUserJoin(EventJoinQuertRequest eventJoinQuertRequest) {
        if(ObjectUtil.isNull(eventJoinQuertRequest.getUserId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户ID为空");
        }
        return this.baseMapper.getEventByUserJoin(eventJoinQuertRequest);
    }

    @Override
    public List<BusEventVO> getEventByUserNotJoin(EventJoinQuertRequest eventJoinQuertRequest) {
        if(ObjectUtil.isNull(eventJoinQuertRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询条件为空");
        }
        if(ObjectUtil.isNull(eventJoinQuertRequest.getUserId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户ID为空");
        }
        eventJoinQuertRequest.setEventEndTime(LocalDateTime.now());
        return this.baseMapper.getEventByUserNotJoin(eventJoinQuertRequest);
    }

    @Override
    public long joinEvent(EventUserJoinRequest request) {
        // 校验参数
        if(ObjectUtil.isNull(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询条件为空");
        }
        // 校验活动
        QueryWrapper<BusEvent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(request.getEventId()), "id", request.getEventId());
        queryWrapper.ge("event_end_time", LocalDateTime.now());
        if(this.baseMapper.selectCount(queryWrapper) == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "活动已结束");
        }
        // 参加活动
        return busEventJoinUserService.joinEvent(request);
    }

    @Override
    public boolean quitEvent(EventUserJoinRequest request) {
        // 校验参数
        if(ObjectUtil.isNull(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询条件为空");
        }
        // 退出活动
        return busEventJoinUserService.quitEvent(request);
    }

}




