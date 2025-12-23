package com.itegg.yougravitybackend.mapper.basic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itegg.yougravitybackend.model.entity.basic.FamousQuote;

/**
 * 名句 mapper类
 * @author ITegg
 */
public interface FamousQuoteMapper extends BaseMapper<FamousQuote> {

    /**
     * 随机获取一条名句
     */
    FamousQuote randomQuote();

}
