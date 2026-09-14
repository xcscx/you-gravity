package com.itegg.yougravitybackend.model.vo.gallery;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 新增图库图片请求参数
 * @author ITegg
 */
@Data
public class GalleryImageAddRequest implements Serializable {

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
