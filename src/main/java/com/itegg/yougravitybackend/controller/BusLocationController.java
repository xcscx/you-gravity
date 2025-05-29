package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.common.AESUtil;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.constant.UserConstant;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationAddRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationQueryRequest;
import com.itegg.yougravitybackend.model.dto.busLocation.LocationUpdateRequest;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.itegg.yougravitybackend.model.vo.AmapVO;
import com.itegg.yougravitybackend.service.BusLocationService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 地点 控制器
 * @author itegg
 */
@RestController
@RequestMapping("/location")
public class BusLocationController {

    @Value("${amap.js.key}")
    private String amapKey;

    @Value("${amap.js.security-code}")
    private String securityCode;

    @Resource
    private BusLocationService busLocationService;

    /***
     * 获取高德地图token
     * @return token信息
     */
    @GetMapping("/amap-token")
    public Result<AmapVO> getAmapToken() throws Exception {
        AmapVO amapVO = new AmapVO();
        amapVO.setKey(AESUtil.encrypt(amapKey));
        amapVO.setSecurityCode(AESUtil.encrypt(securityCode));
        return ResultUtils.ok(amapVO);
    }

    /**
     * 添加地点
     * @param request 请求参数
     * @return 返回地点id
     */
    @PostMapping("/add")
    public Result<Long> addLocation(@RequestBody LocationAddRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busLocationService.addLocation(request));
    }

    /**
     * 更新地点
     * @param request 更新地点请求参数
     * @return 更新结果
     */
    @PostMapping("/update")
    public Result<Boolean> updateLocation(@RequestBody LocationUpdateRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request) || ObjectUtil.isNull(request.getId()), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busLocationService.updateLocation(request));
    }

    /**
     * 获取地点列表
     * @param request 查询参数
     * @return 返回分页结果
     */
    @PostMapping("/list/page")
    public Result<Page<BusLocation>> listLocationByPage(@RequestBody LocationQueryRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<BusLocation> locationPage = busLocationService.page(new Page<>(current, pageSize),
                busLocationService.getLocationList(request));
        return ResultUtils.ok(locationPage);
    }

    /**
     * 获取地点详情
     * @param id 地点id
     * @return 地点详细信息
     */
    @GetMapping("/get")
    public Result<BusLocation> getLocationById(long id) {
        ThrowUtils.throwIf(ObjectUtil.isNull(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busLocationService.getById(id));
    }

    /**
     * 删除地点
     * @param id 地点id
     * @return 删除结果
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> deleteLocation(@RequestBody IdCondition id) {
        if (ObjectUtil.isNull(id) || id.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(busLocationService.removeById(id));
    }

    /**
     * 想去地点
     * @param id 地点id
     * @param request 请求
     * @return 想去id
     */
    @PostMapping("/want-go")
    public Result<Long> wantGo(@RequestBody IdCondition id, HttpServletRequest request) {
        if (ObjectUtil.isNull(id) || id.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(busLocationService.wantGo(id.getId(), request));
    }

    /**
     * 取消想去地点
     */
    @PostMapping("/not-want-go")
    public Result<Boolean> notWantGo(@RequestBody IdCondition id, HttpServletRequest request) {
        if (ObjectUtil.isNull(id) || id.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(busLocationService.notWantGo(id.getId(), request));
    }

    /**
     * 想去地点列表
     */


    /**
     * 打卡地点
     */


    /**
     * 删除打卡
     */


    /**
     * 打卡地点列表
     */


}
