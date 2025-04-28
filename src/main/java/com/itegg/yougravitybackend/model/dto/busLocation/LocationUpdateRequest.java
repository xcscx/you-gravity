package com.itegg.yougravitybackend.model.dto.busLocation;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class LocationUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 活动id
     */
    private Long eventId;

    /**
     * 图片url
     */
    private String url;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地点名称
     */
    private String locationName;

}
