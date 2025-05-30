package com.itegg.yougravitybackend.mapper;

import com.itegg.yougravitybackend.model.dto.busLocation.WantGoQueryRequest;
import com.itegg.yougravitybackend.model.entity.BusLocation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itegg.yougravitybackend.model.vo.BusLocationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ITegg
* @description 针对表【bus_location(地点表)】的数据库操作Mapper
* @createDate 2025-04-21 15:05:10
* @Entity com.itegg.yougravitybackend.model.entity.BusLocation
*/
public interface BusLocationMapper extends BaseMapper<BusLocation> {

    List<BusLocationVO> wantGoList(@Param("condition")WantGoQueryRequest wantGoQueryRequest, @Param("userId") Long userId);

}




