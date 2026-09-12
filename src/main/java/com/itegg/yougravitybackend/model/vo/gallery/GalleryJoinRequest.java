package com.itegg.yougravitybackend.model.vo.gallery;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 加入图库请求参数
 * @author ITegg
 */
@Data
public class GalleryJoinRequest implements Serializable {

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

    /**
     * 用户名称
     */
    private String userName;

}
