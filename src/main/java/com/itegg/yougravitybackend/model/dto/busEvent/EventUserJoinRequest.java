package com.itegg.yougravitybackend.model.dto.busEvent;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventUserJoinRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Long eventId;

    /**
     * 参与用户id
     */
    private Long userId;

}
