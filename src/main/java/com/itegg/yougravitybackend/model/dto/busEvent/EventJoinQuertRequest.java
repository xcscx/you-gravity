package com.itegg.yougravitybackend.model.dto.busEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class EventJoinQuertRequest extends EventQueryRequest {

    /**
     * 用户id
     */
    private Long userId;

}
