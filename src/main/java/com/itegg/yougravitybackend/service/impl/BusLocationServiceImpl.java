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
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.itegg.yougravitybackend.model.entity.BusLocationWantGo;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.service.BusLocationService;
import com.itegg.yougravitybackend.mapper.BusLocationMapper;
import com.itegg.yougravitybackend.service.BusLocationWantGoService;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
* @author ITegg
* @description 针对表【bus_location(地点表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:10
*/
@Service
@RequiredArgsConstructor
public class BusLocationServiceImpl extends ServiceImpl<BusLocationMapper, BusLocation>
    implements BusLocationService{

    @Resource
    private FileManager fileManager;

    @Resource
    private UserService userService;

    @Resource
    private BusLocationWantGoService busLocationWantGoService;

    private final TransactionTemplate transactionTemplate;

    @Override
    public long addLocation(LocationAddRequest req) {
        // 校验参数是否异常
        if(StrUtil.hasBlank(req.getLocationName(), req.getLongitude(), req.getLatitude(), req.getIntroduction())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "地点信息不完整");
        }
        // 校验数据是否重复
        QueryWrapper<BusLocation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location_name", req.getLocationName());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "地点重复");
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
        // 修改数据
        BusLocation location = new BusLocation();
        BeanUtils.copyProperties(req, location);
        return this.baseMapper.updateById(location) > 0;
    }

    @Override
    public QueryWrapper<BusLocation> getLocationList(LocationQueryRequest req) {
        QueryWrapper<BusLocation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(req.getLocationName()), "location_name", req.getLocationName());
        queryWrapper.eq(ObjectUtil.isNotEmpty(req.getLongitude()), "longitude", req.getLongitude());
        queryWrapper.eq(ObjectUtil.isNotEmpty(req.getLatitude()), "latitude", req.getLatitude());
        return queryWrapper;
    }

    @Override
    public Long wantGo(Long id, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        BusLocationWantGo wantGo = new BusLocationWantGo();
        wantGo.setLocationId(id);
        wantGo.setUserId(loginUser.getId());

        synchronized (loginUser.getId().toString().intern()) {
            return transactionTemplate.execute(status -> {
                BusLocation busLocation = this.getById(id);
                boolean exists = busLocationWantGoService.lambdaQuery()
                                .eq(BusLocationWantGo::getLocationId, id)
                                    .eq(BusLocationWantGo::getUserId, loginUser.getId())
                                        .exists();
                if(exists) {
                    throw new RuntimeException("你已经添加想去了");
                }

                busLocationWantGoService.save(wantGo);
                busLocation.setWantGoCount(busLocation.getWantGoCount() + 1);
                this.updateById(busLocation);
                return wantGo.getId();
            });
        }
    }

    @Override
    public Boolean notWantGo(Long id, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        // 加锁删除想去 同时 想去数目-1
        synchronized (loginUser.getId().toString().intern()) {
            return transactionTemplate.execute(status -> {
                BusLocationWantGo wantGo = busLocationWantGoService.lambdaQuery()
                        .eq(BusLocationWantGo::getUserId, loginUser.getId())
                        .eq(BusLocationWantGo::getLocationId, id)
                        .one();
                if(ObjectUtil.isNull(wantGo)) {
                    throw new RuntimeException("该地点目前不在你的想去地点中");
                }
                boolean update = this.lambdaUpdate()
                        .eq(BusLocation::getId, id)
                        .setSql("want_go_count = want_go_count - 1")
                        .update();
                return update && busLocationWantGoService.removeById(wantGo.getId());
            });
        }
    }

}




