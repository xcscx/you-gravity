package com.itegg.yougravitybackend.model.dto.space;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpaceUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间级别：0-普通 1-专业 2-旗舰
     */
    private Integer spaceLevel;

    /**
     * 空间图片最大大小
     */
    private Long maxSize;

    /**
     * 空间图片最大数量
     */
    private Long maxCount;

}
