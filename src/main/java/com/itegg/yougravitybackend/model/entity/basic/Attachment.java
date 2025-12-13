package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件表 basic_attachment
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_attachment")
@Data
public class Attachment extends SuperModel {

    /**
     * 关联模块
     */
    @TableField("biz_type")
    private String bizType;

    /**
     * 关联业务id
     */
    @TableField("biz_id")
    private Long bizId;

    /**
     * 附件名称
     */
    @TableField("original_name")
    private String originalName;

    /**
     * 附件完整url
     */
    @TableField("file_url")
    private String fileUrl;

    /**
     * 存储路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件扩展名
     */
    @TableField("file_ext")
    private String fileExt;

    /**
     * 创建人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 状态 1-启用 0-禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 信息
     */
    @TableField("message")
    private String message;

}
