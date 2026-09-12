package com.itegg.yougravitybackend.model.entity.gallery;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图库成员表 gallery_member
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="gallery_member")
@Data
public class GalleryMember extends SuperModel {

    /**
     * 图库id
     */
    @TableField("gallery_id")
    private Long galleryId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 创建人id
     */
    @TableField("create_by")
    private Long createBy;

}
