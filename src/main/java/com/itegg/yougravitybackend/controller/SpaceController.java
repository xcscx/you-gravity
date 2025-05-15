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
import com.itegg.yougravitybackend.model.dto.space.SpaceAddRequest;
import com.itegg.yougravitybackend.model.dto.space.SpaceEditRequest;
import com.itegg.yougravitybackend.model.dto.space.SpaceQueryRequest;
import com.itegg.yougravitybackend.model.dto.space.SpaceUpdateRequest;
import com.itegg.yougravitybackend.model.entity.Space;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.vo.SpaceVO;
import com.itegg.yougravitybackend.service.SpaceService;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/space")
public class SpaceController {

    @Resource
    private UserService userService;

    @Resource
    private SpaceService spaceService;

    /**
     * 添加空间
     * @param add 新增空间请求参数
     * @param request 请求
     * @return 新增空间id
     */
    @PostMapping("/add")
    public Result<Long> addSpace(@RequestBody SpaceAddRequest add, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.ok(spaceService.addSpace(add, loginUser));
    }

    /**
     * 管理员修改空间信息（level等）
     * @param request 更改请求
     * @return 是否成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> udpateSpace(@RequestBody SpaceUpdateRequest request) {
        if(request == null || request.getId() == null || request.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 判断是否存在
        long id = request.getId();
        Space oldSpace = spaceService.getById(id);
        ThrowUtils.throwIf(oldSpace == null, ErrorCode.NOT_FOUND_ERROR);
        // 转换数据类型
        Space space = new Space();
        BeanUtils.copyProperties(request, space);
        // 填充数据
        spaceService.fillSpaceBySpaceLevel(space);
        // 校验数据
        spaceService.validSpace(space, false);
        // 更改数据
        boolean result = spaceService.updateById(space);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 删除数据--仅创建者本人和管理员可以删除
     * @param id 被删除空间的id
     * @param request 请求
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteSpace(@RequestBody IdCondition id, HttpServletRequest request) {
        if(ObjectUtil.isNull(id) || id.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 管理员和自己都可以删除
        User loginUser = userService.getLoginUser(request);
        Long SpaceId = id.getId();
        Space oldSpace = spaceService.getById(SpaceId);
        ThrowUtils.throwIf(ObjectUtil.isNull(oldSpace), ErrorCode.NOT_FOUND_ERROR);
        // 权限校验
        if(!oldSpace.getCreateBy().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw  new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 删除数据
        boolean result = spaceService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 仅限创建者本人可以修改
     * @param edit 编辑内容
     * @param request 请求参数
     * @return 修改结果
     */
    @PostMapping("/edit")
    public Result<Boolean> editSpace(@RequestBody SpaceEditRequest edit, HttpServletRequest request) {
        // 校验数据参数
        if(ObjectUtil.isNull(edit) || edit.getId() < 0 || edit.getSpaceName() == null || edit.getSpaceName().length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 仅限本人可编辑
        User loginUser = userService.getLoginUser(request);
        long id = edit.getId();
        Space oldSpace = spaceService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(oldSpace), ErrorCode.NOT_FOUND_ERROR);
        if(!oldSpace.getCreateBy().equals(loginUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 转换数据
        Space space = new Space();
        BeanUtils.copyProperties(edit, space);
        boolean result = spaceService.updateById(space);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 依据id获取空间 - 仅管理员使用
     * @param id 空间id
     * @param request 请求
     * @return 空间信息
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Space> getSpaceById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Space space = spaceService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(space), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.ok(space);
    }

    /**
     * 获取空间vo信息
     * @param id 空间id
     * @param request 请求
     * @return vo空间信息
     */
    @GetMapping("/get/vo")
    public Result<SpaceVO> getSpaceVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Space space = spaceService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(space), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.ok(SpaceVO.objToVo(space));
    }

    /**
     * 分页查询空间信息
     * @param request 查询请求参数
     * @return 分页结果
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Page<Space>> listSpaceByPage(@RequestBody SpaceQueryRequest request) {
        long current = request.getCurrent();
        long size = request.getPageSize();
        // 查询
        Page<Space> spacePage = spaceService.page(new Page<>(current, size),
                spaceService.getSpaceQueryWrapper(request));
        return ResultUtils.ok(spacePage);
    }

    @PostMapping("/list/page/vo")
    public Result<Page<SpaceVO>> listSpaceVOByPage(@RequestBody SpaceQueryRequest request, HttpServletRequest httpServletRequest) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = request.getCurrent();
        long size = request.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Space> spacePage = spaceService.page(new Page<>(current, size),
                spaceService.getSpaceQueryWrapper(request));
        return ResultUtils.ok(spaceService.getSpaceVOPage(spacePage));
     }

}
