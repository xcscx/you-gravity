package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.model.entity.BusEvent;
import com.itegg.yougravitybackend.service.BusEventService;
import com.itegg.yougravitybackend.mapper.BusEventMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_event(活动表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:08
*/
@Service
public class BusEventServiceImpl extends ServiceImpl<BusEventMapper, BusEvent>
    implements BusEventService{

}




