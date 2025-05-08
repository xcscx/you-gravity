package com.itegg.yougravitybackend.model.dto.busLocation;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class LocationQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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
