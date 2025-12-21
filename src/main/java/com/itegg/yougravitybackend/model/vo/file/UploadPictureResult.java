package com.itegg.yougravitybackend.model.vo.file;
import lombok.Data;

/**
 * 上传图片的结果
 */
@Data
public class UploadPictureResult {

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 图片体积
     */
    private Long picSize;

    /**
     * 图片宽度
     */
    private Integer picWidth;

    /**
     * 图片高度
     */
    private Integer picHeight;

    /**
     * 图片宽高比
     */
    private Double picScale;

    /**
     * 图片格式
     */
    private String picFormat;

}
