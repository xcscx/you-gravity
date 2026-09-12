package com.itegg.yougravitybackend.model.entity.gallery;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图库图片表 gallery_image
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="gallery_image")
@Data
public class GalleryImage extends SuperModel {

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * 图片名称
     */
    @TableField("name")
    private String name;

    /**
     * 标签
     */
    @TableField("tags")
    private String tags;

    /**
     * 图片体积
     */
    @TableField("pic_size")
    private Long picSize;

    /**
     * 图片宽度
     */
    @TableField("pic_width")
    private Long picWidth;

    /**
     * 图片高度
     */
    @TableField("pic_height")
    private Long picHeight;

    /**
     * 图片宽高比
     */
    @TableField("pic_scale")
    private String picScale;

    /**
     * 图片格式
     */
    @TableField("pic_format")
    private String picFormat;

    /**
     * 图库id
     */
    @TableField("gallery_id")
    private Long galleryId;

    /**
     * 创建人id
     */
    @TableField("create_by")
    private Long createBy;

}
