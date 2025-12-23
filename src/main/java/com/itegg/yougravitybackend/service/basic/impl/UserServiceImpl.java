package com.itegg.yougravitybackend.service.basic.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.user.UserLoginRequest;
import com.itegg.yougravitybackend.model.vo.user.UserRegisterRequest;
import com.itegg.yougravitybackend.model.entity.basic.User;
import com.itegg.yougravitybackend.model.vo.user.LoginUserVO;
import com.itegg.yougravitybackend.model.vo.user.UserVO;
import com.itegg.yougravitybackend.service.basic.UserService;
import com.itegg.yougravitybackend.mapper.basic.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;

import static com.itegg.yougravitybackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public long userRegister(UserRegisterRequest req) {
        // 校验数据是否合理
        if(StrUtil.hasBlank(req.getMobile(), req.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 校验数据是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", req.getMobile());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 默认用户名称设置（年后两位 + 月 + 六位随机数） + 密码加密
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear()).substring(2);
        String month = String.format("%02d", currentDate.getMonthValue());
        String randomPart = RandomUtil.randomNumbers(6);
        String encryptPassword = getEncryptPassword(req.getPassword(), randomPart);
        // 插入数据
        User user = new User();
        user.setMobile(req.getMobile());
        user.setPassword(encryptPassword);
        user.setSalt(randomPart);
        user.setName("默认用户" + year + month + randomPart);
        // TODO 用户角色配置
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
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", req.getUserAccount());
        User user = this.baseMapper.selectOne(queryWrapper);
        if(ObjectUtil.isNull(user) || !user.getPassword().equals(getEncryptPassword(req.getUserPassword(), user.getSalt()))) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 记录用户的登录态 TODO 修改为sa-token
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        Object userOjb = request.getSession().getAttribute(USER_LOGIN_STATE);
        if(ObjectUtil.isNull(userOjb)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态 TODO 修改为sa-token
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public String getEncryptPassword(String userPassword, String salt) {
        return DigestUtils.md5DigestAsHex((salt + userPassword).getBytes());
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

//    @Override
//    public List<UserVO> getUserVOList(List<User> userList) {
//        if(ObjectUtil.isNull(userList)) {
//            return new ArrayList<>();
//        }
//        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
//    }

//    @Override
//    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
//        if(ObjectUtil.isNull(userQueryRequest)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(ObjectUtil.isNotNull(userQueryRequest.getId()), "id", userQueryRequest.getId());
//        queryWrapper.eq(StrUtil.isNotBlank(userQueryRequest.getUserRole()), "user_role", userQueryRequest.getUserRole());
//        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserAccount()), "user_account", userQueryRequest.getUserAccount());
//        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserName()), "user_name", userQueryRequest.getUserName());
//        queryWrapper.like(StrUtil.isNotBlank(userQueryRequest.getUserProfile()), "user_profile", userQueryRequest.getUserProfile());
//        queryWrapper.orderBy(StrUtil.isNotEmpty(userQueryRequest.getSortOrder()), userQueryRequest.getSortOrder().equals("asc"), userQueryRequest.getSortField());
//        return queryWrapper;
//    }

//    @Override
//    public boolean isAdmin(User user) {
////        return ObjectUtil.isNotNull(user) && UserRoleEnum.ADMIN.getCode().equals(user.getUserRole());
//    return true;
//    }

}




