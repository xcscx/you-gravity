package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.itegg.yougravitybackend.service.BusLocationService;
import com.itegg.yougravitybackend.mapper.BusLocationMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_location(地点表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:10
*/
@Service
public class BusLocationServiceImpl extends ServiceImpl<BusLocationMapper, BusLocation>
    implements BusLocationService{

}




