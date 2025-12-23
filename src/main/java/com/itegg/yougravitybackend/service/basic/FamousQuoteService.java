package com.itegg.yougravitybackend.service.basic;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.basic.FamousQuote;

/**
 * 名句 Service层
 * @author ITegg
 */
public interface FamousQuoteService extends IService<FamousQuote> {

    /**
     * 随机获取一条名句
     * @return 名句
     */
    FamousQuote randomQuote();

}
