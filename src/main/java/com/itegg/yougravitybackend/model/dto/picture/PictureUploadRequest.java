package com.itegg.yougravitybackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureUploadRequest implements Serializable {

    private static final long serivalVersionUID = 1L;

    /**
     * 图片id(用于修改)
     */
    private Long id;

}
