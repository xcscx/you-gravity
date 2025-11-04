package com.itegg.yougravitybackend.aop.aspect;


import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     * @param joinPoint 切入点
     * @param authCheck 权限校验注解
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        String mustRole = authCheck.mustRole();
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        // 当前登录用户
//        User loginUser = userService.getLoginUser(request);
//        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByCode(mustRole);
//        // 不需要权限,放行
//        if(ObjectUtil.isNull(mustRoleEnum)) {
//            return joinPoint.proceed();
//        }
//        // 获取当前用户具有的权限
//        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByCode(loginUser.getUserRole());
//        // 没有权限,拒绝
//        if(ObjectUtil.isNull(userRoleEnum)) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        // 要求必须要管理员权限
//        if(UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
        // 通过权限校验
        return joinPoint.proceed();
    }
}
