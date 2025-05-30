package com.itegg.yougravitybackend.model.dto.busLocation;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class LocationQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地点名称
     */
    private String locationName;

    /**
     * 最少想去人数
     */
    private Integer wantGoCountMin;

    // --- 坐标参数 ---

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    // --- 标识参数 ---

    /**
     * 是否是想去地点
     */
    private Boolean wantGo;

    /**
     * 是否是打卡过的地点
     */
    private Boolean checkIn;

}
