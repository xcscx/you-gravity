package com.itegg.yougravitybackend.service.basic.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.basic.SignInMapper;
import com.itegg.yougravitybackend.model.entity.basic.FamousQuote;
import com.itegg.yougravitybackend.model.entity.basic.SignIn;
import com.itegg.yougravitybackend.model.enums.LuckEnum;
import com.itegg.yougravitybackend.model.vo.user.SignInVO;
import com.itegg.yougravitybackend.service.basic.FamousQuoteService;
import com.itegg.yougravitybackend.service.basic.SignInService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 登录Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn>
    implements SignInService {

    @Resource
    private FamousQuoteService famousQuoteService;

    @Override
    public SignInVO signIn(Long userId, LocalDate date) {
        // 校验是否签到过
        QueryWrapper<SignIn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("date", date);
        SignIn info = this.getOne(queryWrapper);
        if (ObjectUtil.isNotNull(info)) {
            FamousQuote byId = famousQuoteService.getById(info.getFamousQuoteId());
            SignInVO signInVO = BeanUtil.toBean(info, SignInVO.class);
            signInVO.setContent(byId.getContent());
            signInVO.setAuthor(byId.getAuthor());
            signInVO.setSource(byId.getSource());
            return signInVO;
        }

        // 获取名句
        FamousQuote famousQuote = famousQuoteService.randomQuote();
        // 获取幸运
        LuckEnum luck = LuckEnum.getDailyFortune(userId);
        // 添加签到
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setDate(date);
        signIn.setLuck(luck.getName());
        signIn.setBackgroundColor(luck.getColor());
        signIn.setDescription(luck.getDescription());
        signIn.setFamousQuoteId(famousQuote.getId());
        boolean save = save(signIn);
        if (!save) {
            log.error("=========> 签到失败: {}", JSONUtil.toJsonStr(signIn));
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "签到失败");
        }

        SignInVO signInVO = new SignInVO();
        signInVO.setUserId(userId);
        signInVO.setDate(date);
        signInVO.setLuck(luck.getName());
        signInVO.setBackgroundColor(luck.getColor());
        signInVO.setDescription(luck.getDescription());
        signInVO.setFamousQuoteId(famousQuote.getId());
        signInVO.setContent(famousQuote.getContent());
        signInVO.setAuthor(famousQuote.getAuthor());
        signInVO.setSource(famousQuote.getSource());

        return signInVO;
    }

}
