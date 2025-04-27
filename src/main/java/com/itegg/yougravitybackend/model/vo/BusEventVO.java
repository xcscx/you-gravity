package com.itegg.yougravitybackend.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class BusEventVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
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

    /**
     * 活动背景图片
     */
    private String eventBackgroundImage;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
