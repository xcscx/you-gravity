package com.itegg.yougravitybackend.model.dto.space;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpaceAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间级别 0-普通 1-专业 2-旗舰
     */
    private Integer spaceLevel;

}
