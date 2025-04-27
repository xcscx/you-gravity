package com.itegg.yougravitybackend.model.dto.busEvent;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EventUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Long id;

    /**
     * 活动名称
     */
    private String eventName;

    /**
     * 活动地点
     */
    private String eventCity;

    /**
     * 开始时间
     */
    private LocalDateTime eventStartTime;

    /**
     * 结束时间
     */
    private LocalDateTime eventEndTime;

}
