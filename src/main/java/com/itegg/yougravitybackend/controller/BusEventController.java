package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.constant.UserConstant;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.dto.busEvent.*;
import com.itegg.yougravitybackend.model.entity.BusEvent;
import com.itegg.yougravitybackend.model.vo.BusEventVO;
import com.itegg.yougravitybackend.service.BusEventService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 活动 控制器
 * @author itegg
 */
@RestController
@RequestMapping("/event")
public class BusEventController {

    @Resource
    private BusEventService busEventService;

    /**
     * 新增活动
     * @param request 新增活动的参数
     * @return 活动id
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Long> addEvent(@RequestBody EventAddRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busEventService.addBusEvent(request));
    }

    /**
     * 编辑活动
     * @param request 编辑活动的参数
     * @return 编辑结果
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> updateEvent(@RequestBody EventUpdateRequest request) {
        if(ObjectUtil.isNull(request) || ObjectUtil.isNull(request.getId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        BusEvent event = new BusEvent();
        BeanUtils.copyProperties(request, event);
        boolean result = busEventService.updateById(event);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 删除活动
     * @param id 活动id - 仅限管理员调用
     * @return 删除结果
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> deleteEvent(@RequestBody IdCondition id) {
        if(ObjectUtil.isNull(id) || id.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(busEventService.removeById(id));
    }

    /**
     * 全部活动列表
     * @param request 分页请求参数
     * @return 分页活动数据列表
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Page<BusEvent>> listEventByPage(@RequestBody EventQueryRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<BusEvent> eventPage = busEventService.page(new Page<>(current, pageSize),
                busEventService.getEventList(request));
        return ResultUtils.ok(eventPage);
    }

    /**
     * 获取活动详情
     * @param id 活动id
     * @return 活动详情
     */
    @GetMapping("/get")
    public Result<BusEventVO> getEventById(long id) {
        ThrowUtils.throwIf(ObjectUtil.isNull(id), ErrorCode.PARAMS_ERROR);
        BusEvent byId = busEventService.getById(id);
        BusEventVO vo = new BusEventVO();
        BeanUtils.copyProperties(byId, vo);
        return ResultUtils.ok(vo);
    }

    /**
     * 当前用户参与了的活动列表
     * @param request 请求参数
     * @return 符合当前用户已参加的活动列表
     */
    @PostMapping("/my/join")
    public Result<Page<BusEventVO>> listMyJoinEventVOByPage(@RequestBody EventJoinQuertRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<BusEventVO> eventPage = new Page<>(current, pageSize);
        eventPage.setRecords(busEventService.getEventByUserJoin(request));
        return ResultUtils.ok(eventPage);
    }

    /**
     * 当前用户没有参与，活动也没有结束的活动列表
     * @param request 请求参数
     * @return 当前用户还未参加，但是可以参加的活动列表
     */
    @PostMapping("/can/join")
    public Result<Page<BusEventVO>> listMyCanJoinEventVOByPage(@RequestBody EventJoinQuertRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<BusEventVO> eventPage = new Page<>(current, pageSize);
        eventPage.setRecords(busEventService.getEventByUserNotJoin(request));
        return ResultUtils.ok(eventPage);
    }

    /**
     * 参加活动
     * @param request 参加活动请求参数
     * @return 参加结果
     */
    @PostMapping("/join")
    public Result<Boolean> joinEvent(@RequestBody EventUserJoinRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busEventService.joinEvent(request) > 0);
    }

    /**
     * 退出活动
     * @param request 退出活动请求参数
     * @return 退出结果
     */
    @PostMapping("/quit")
    public Result<Boolean> quitEvent(@RequestBody EventUserJoinRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busEventService.quitEvent(request));
    }

}
