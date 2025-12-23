package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.constant.UserConstant;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.vo.user.*;
import com.itegg.yougravitybackend.model.entity.basic.User;
import com.itegg.yougravitybackend.model.vo.user.LoginUserVO;
import com.itegg.yougravitybackend.model.vo.user.UserVO;
import com.itegg.yougravitybackend.service.basic.SignInService;
import com.itegg.yougravitybackend.service.basic.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * 用户 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SignInService signInService;

    /**
     * 用户注册接口
     * @param request 注册请求参数
     * @return 注册用户的id
     */
    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody UserRegisterRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(userService.userRegister(request));
    }


    /**
     * 用户签到
     */
    @PostMapping("/signIn")
    public Result<SignInVO> signIn(@RequestBody IdCondition id) {
        ThrowUtils.throwIf(ObjectUtil.isNull(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(signInService.signIn(id.getId(), LocalDate.now()));
    }


    // -------------------------------------------------------

    /**
     * 用户登录接口
     * @param userLoginRequest 登录请求参数
     * @param request http请求
     * @return 用户脱敏数据
     */
    @PostMapping("/login")
    public Result<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(userLoginRequest), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(userService.userLogin(userLoginRequest, request));
    }

    /**
     * 获取登录用户
     * @param request http请求参数
     * @return 用户信息
     */
    @GetMapping("/get/login")
    public Result<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.ok(userService.getLoginUserVO(user));
    }

    /**
     * 用户登出接口
     * @param request http请求
     * @return 是否成功登出
     */
    @GetMapping("/get/logout")
    public Result<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(userService.userLogout(request));
    }

    /**
     * -----   管理员接口   -----
     */

    /**
     * 创建用户 - 仅管理员可调用
     * @param userAddRequest 创建用户数据信息
     * @return 用户id
     */
//    @PostMapping("/add")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public Result<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
//        ThrowUtils.throwIf(ObjectUtil.isNull(userAddRequest), ErrorCode.PARAMS_ERROR);
//        User user = new User();
//        BeanUtils.copyProperties(userAddRequest, user);
//        // 默认密码 123456
//        final String DEFAULT_PASSWORD = "123456";
//        String encryptPassword = userService.getEncryptPassword(DEFAULT_PASSWORD);
//        user.setUserPassword(encryptPassword);
//        boolean result = userService.save(user);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        return ResultUtils.ok(user.getId());
//    }

    /**
     * 依据id获取用户 - 仅管理员可调用
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<User> getUserById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(user), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.ok(user);
    }

    /**
     * 依据id获取包装类 - 仅管理员可调用
     * @param id 用户id
     * @return 用户vo信息
     */
    @GetMapping("/get/vo")
    public Result<UserVO> getUserVOById(long id) {
        Result<User> result = getUserById(id);
        User user = result.getData();
        return ResultUtils.ok(userService.getUserVO(user));
    }

    /**
     * 依据id删除用户 - 仅管理员可调用
     * @param idCondition id信息
     * @return 删除是否成功标识
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> deleteUser(@RequestBody IdCondition idCondition) {
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(idCondition.getId());
        return ResultUtils.ok(b);
    }

    /**
     * 更新用户 - 仅管理员可调用
     * @param userUpdateRequest 更新用户请求
     * @return 返回是否更新成功
     */
//    @PostMapping("/update")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public Result<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
//        if(ObjectUtil.isNull(userUpdateRequest) || ObjectUtil.isNull(userUpdateRequest.getId())) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        User user = new User();
//        BeanUtils.copyProperties(userUpdateRequest, user);
//        boolean result = userService.updateById(user);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        return ResultUtils.ok(true);
//    }

    /**
     * 分页获取用户封装 - 仅管理员可调用
     * @param userQueryRequest 用户搜素参数
     * @return 分页搜素
     */
//    @PostMapping("/list/page/vo")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public Result<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
//        ThrowUtils.throwIf(ObjectUtil.isNull(userQueryRequest), ErrorCode.PARAMS_ERROR);
//        long current = userQueryRequest.getCurrent();
//        long pageSize = userQueryRequest.getPageSize();
//        Page<User> userPage = userService.page(new Page<>(current, pageSize),
//                userService.getQueryWrapper(userQueryRequest));
//        Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
//        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
//        userVOPage.setRecords(userVOList);
//        return ResultUtils.ok(userVOPage);
//    }

}
