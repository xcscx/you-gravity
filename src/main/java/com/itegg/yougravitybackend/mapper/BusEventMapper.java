package com.itegg.yougravitybackend.mapper;

import com.itegg.yougravitybackend.model.dto.busEvent.EventJoinQuertRequest;
import com.itegg.yougravitybackend.model.entity.BusEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itegg.yougravitybackend.model.vo.BusEventVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ITegg
* @description 针对表【bus_event(活动表)】的数据库操作Mapper
* @createDate 2025-04-21 15:05:08
* @Entity com.itegg.yougravitybackend.model.entity.BusEvent
*/
public interface BusEventMapper extends BaseMapper<BusEvent> {

    List<BusEventVO> getEventByUserJoin(@Param("eventJoinQuertRequest") EventJoinQuertRequest condition);

    List<BusEventVO> getEventByUserNotJoin(@Param("eventJoinQuertRequest") EventJoinQuertRequest condition);


}




