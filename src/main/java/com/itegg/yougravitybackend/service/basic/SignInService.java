package com.itegg.yougravitybackend.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.basic.SignIn;
import com.itegg.yougravitybackend.model.vo.user.SignInVO;

import java.time.LocalDate;

/**
 * 每日登录 Service层
 * @author ITegg
 */
public interface SignInService extends IService<SignIn> {

    /**
     * 签到
     * @param userId 用户id
     * @param date 日期
     * @return 签到信息
     */
    SignInVO signIn(Long userId, LocalDate date);

}
