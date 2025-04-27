package com.itegg.yougravitybackend.model.dto.busEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class EventJoinQuertRequest extends EventQueryRequest {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关联活动id列表
     */
    private List<Long> eventIdList;

}
