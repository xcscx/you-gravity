package com.itegg.yougravitybackend.model.vo.gallery;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图库等级列表返回参数 vo
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GalleryLevelListVO extends PageRequest implements Serializable {

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
