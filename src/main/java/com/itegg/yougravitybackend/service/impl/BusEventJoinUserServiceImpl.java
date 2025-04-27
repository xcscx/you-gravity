package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.busEvent.EventUserJoinRequest;
import com.itegg.yougravitybackend.model.entity.BusEventJoinUser;
import com.itegg.yougravitybackend.service.BusEventJoinUserService;
import com.itegg.yougravitybackend.mapper.BusEventJoinUserMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_event_join_user(活动参与表)】的数据库操作Service实现
* @createDate 2025-04-22 11:58:36
*/
@Service
public class BusEventJoinUserServiceImpl extends ServiceImpl<BusEventJoinUserMapper, BusEventJoinUser>
    implements BusEventJoinUserService{

    @Override
    public long joinEvent(EventUserJoinRequest request) {
        // 校验数据是否合理
        if(StrUtil.hasBlank(request.getEventId().toString(), request.getUserId().toString())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请选择好参加的 活动/用户");
        }
        // 校验数据是否重复
        QueryWrapper<BusEventJoinUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_id", request.getEventId());
        queryWrapper.eq("user_id", request.getUserId());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请勿重复参加");
        }
        BusEventJoinUser busEventJoinUser = new BusEventJoinUser();
        busEventJoinUser.setEventId(request.getEventId());
        busEventJoinUser.setUserId(request.getUserId());
        boolean saveResult = this.save(busEventJoinUser);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "参加失败");
        }
        return busEventJoinUser.getId();
    }

    @Override
    public boolean quitEvent(EventUserJoinRequest request) {
        // 校验数据是否合理
        if(StrUtil.hasBlank(request.getEventId().toString(), request.getUserId().toString())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请选择好需要退出的 活动/用户");
        }
        // 校验数据是否重复
        QueryWrapper<BusEventJoinUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_id", request.getEventId());
        queryWrapper.eq("user_id", request.getUserId());
        int result = this.baseMapper.delete(queryWrapper);
        if(result == 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "退出失败");
        }
        return true;
    }

}




