package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.manager.FileManager;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationAddRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationQueryRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationUpdateRequest;
import com.itegg.yougravitybackend.model.dto.file.UploadPictureResult;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.itegg.yougravitybackend.service.BusLocationService;
import com.itegg.yougravitybackend.mapper.BusLocationMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_location(地点表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:10
*/
@Service
public class BusLocationServiceImpl extends ServiceImpl<BusLocationMapper, BusLocation>
    implements BusLocationService{

    @Resource
    private FileManager fileManager;

    @Override
    public long addLocation(LocationAddRequest req) {
        // 校验参数是否异常
        if(StrUtil.hasBlank(req.getEventId().toString(), req.getLocationName(), req.getLongitude(), req.getLatitude(), req.getIntroduction())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "地点信息不完整");
        }
        // 校验数据是否重复
        QueryWrapper<BusLocation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location_name", req.getLocationName());
        queryWrapper.eq("event_id",req.getEventId());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "地点重复");
        }
        // TODO: 保存图片
        if(ObjectUtil.isNotNull(req.getUrl())) {
            String uploadPathPrefix = String.format("location/%s",  req.getEventId());
//            UploadPictureResult uploadPictureResult = fileManager.uploadPictureResult(req.getUrl(), uploadPathPrefix);
        }
        // 添加数据
        BusLocation location = new BusLocation();
        BeanUtils.copyProperties(req, location);
        boolean saveResult = this.save(location);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常，地点添加失败");
        }
        return location.getId();
    }

    @Override
    public boolean updateLocation(LocationUpdateRequest req) {
        // 校验参数是否异常
        if(ObjectUtil.isNull(req.getId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请明确需要修改的地点");
        }
        // TODO: 保存图片
        if(ObjectUtil.isNotNull(req.getUrl())) {
            String uploadPathPrefix = String.format("location/%s",  req.getEventId());
//            UploadPictureResult uploadPictureResult = fileManager.uploadPictureResult(req.getUrl(), uploadPathPrefix);
        }
        // 修改数据
        BusLocation location = new BusLocation();
        BeanUtils.copyProperties(req, location);
        return this.baseMapper.updateById(location) > 0;
    }

    @Override
    public QueryWrapper<BusLocation> getLocationList(LocationQueryRequest req) {
        QueryWrapper<BusLocation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(req.getEventId()), "event_id", req.getEventId());
        queryWrapper.like(ObjectUtil.isNotEmpty(req.getLocationName()), "location_name", req.getLocationName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(req.getLongitude()), "longitude", req.getLongitude());
        queryWrapper.eq(ObjectUtil.isNotEmpty(req.getLatitude()), "latitude", req.getLatitude());
        return queryWrapper;
    }

}




