package com.itegg.yougravitybackend.service.basic.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.basic.FamousQuoteMapper;
import com.itegg.yougravitybackend.model.entity.basic.FamousQuote;
import com.itegg.yougravitybackend.service.basic.FamousQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 名句Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class FamousQuoteServiceImpl extends ServiceImpl<FamousQuoteMapper, FamousQuote>
    implements FamousQuoteService {

    @Override
    public FamousQuote randomQuote() {
        return baseMapper.randomQuote();
    }

}
