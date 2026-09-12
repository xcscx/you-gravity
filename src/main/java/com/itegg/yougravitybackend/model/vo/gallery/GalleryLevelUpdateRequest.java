package com.itegg.yougravitybackend.model.vo.gallery;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图库等级修改参数
 * @author ITegg
 */
@Data
public class GalleryLevelUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图库id
     */
    private Long galleryId;

    /**
     * 用户id
     */
    private Long userId;

}
