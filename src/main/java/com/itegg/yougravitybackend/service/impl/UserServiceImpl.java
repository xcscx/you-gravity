package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.user.UserLoginRequest;
import com.itegg.yougravitybackend.model.dto.user.UserQueryRequest;
import com.itegg.yougravitybackend.model.dto.user.UserRegisterRequest;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.enums.UserRoleEnum;
import com.itegg.yougravitybackend.model.vo.LoginUserVO;
import com.itegg.yougravitybackend.model.vo.UserVO;
import com.itegg.yougravitybackend.service.UserService;
import com.itegg.yougravitybackend.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.itegg.yougravitybackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author ITegg
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:13
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public long userRegister(UserRegisterRequest req) {
        // 校验数据是否合理
        if(StrUtil.hasBlank(req.getUserAccount(), req.getUserPassword(), req.getCheckPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if(ObjectUtil.notEqual(req.getUserPassword(), req.getCheckPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码输入不一致");
        }
        // 校验数据是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", req.getUserAccount());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 数据加密 + 默认用户名称设置（年后两位 + 月 + 六位随机数）
        String encryptPassword = getEncryptPassword(req.getUserPassword());
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear()).substring(2);
        String month = String.format("%02d", currentDate.getMonthValue());
        String randomPart = RandomUtil.randomNumbers(6);
        // 插入数据
        User user = new User();
        user.setUserAccount(req.getUserAccount());
        user.setUserPassword(encryptPassword);
        user.setUserName("默认用户" + year + month + randomPart);
        user.setUserRole(UserRoleEnum.USER.getCode());
        boolean saveResult = this.save(user);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常，数据注册失败");
        }
        return user.getId();
    }

    @Override
    public LoginUserVO userLogin(UserLoginRequest req, HttpServletRequest request) {
        // 校验参数是否合理
        if(StrUtil.hasBlank(req.getUserAccount(), req.getUserPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 加密密码
        String encryptPassword = getEncryptPassword(req.getUserPassword());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", req.getUserAccount());
        queryWrapper.eq("user_password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        if(ObjectUtil.isNull(user)) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        Object userOjb = request.getSession().getAttribute(USER_LOGIN_STATE);
        if(ObjectUtil.isNull(userOjb)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public String getEncryptPassword(String userPassword) {
        // TODO: 盐值转换为各个用户独有各自的盐值
        final String SALT = "itegg";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 判断是否登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(ObjectUtil.isNull(currentUser) || ObjectUtil.isNull(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if(ObjectUtil.isNull(user)) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if(ObjectUtil.isNull(user)) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if(ObjectUtil.isNull(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if(ObjectUtil.isNull(userQueryRequest)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(userQueryRequest.getId()), "id", userQueryRequest.getId());
        queryWrapper.eq(StrUtil.isNotBlank(userQueryRequest.getUserRole()), "user_role", userQueryRequest.getUserRole());
        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserAccount()), "user_account", userQueryRequest.getUserAccount());
        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserName()), "user_name", userQueryRequest.getUserName());
        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserProfile()), "user_profile", userQueryRequest.getUserProfile());
        queryWrapper.orderBy(StrUtil.isNotEmpty(userQueryRequest.getSortOrder()), userQueryRequest.getSortOrder().equals("asc"), userQueryRequest.getSortField());
        return queryWrapper;
    }

    @Override
    public boolean isAdmin(User user) {
        return ObjectUtil.isNotNull(user) && UserRoleEnum.ADMIN.getCode().equals(user.getUserRole());
    }

}




