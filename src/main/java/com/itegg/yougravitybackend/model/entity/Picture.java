package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

/**
 * 图片表
 * @TableName picture
 */
@TableName(value ="picture")
@Data
public class Picture extends superModel {

    /**
     * 图片url
     */
    @TableField("url")
    private String url;

    /**
     * 图片名称
     */
    @TableField("name")
    private String name;

    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 分类
     */
    @TableField("category")
    private String category;

    /**
     * 标签（JSON数组）
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
    private Integer picWidth;

    /**
     * 图片高度
     */
    @TableField("pic_height")
    private Integer picHeight;

    /**
     * 图片宽高比
     */
    @TableField("pic_scale")
    private Double picScale;

    /**
     * 图片格式
     */
    @TableField("pic_format")
    private String picFormat;

    /**
     * 创建用户 id
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 审核状态: 0-待审核 1-通过 2-拒绝
     */
    @TableField("review_status")
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    @TableField("review_message")
    private String reviewMessage;

    /**
     * 审核人 ID
     */
    @TableField("reviewer_id")
    private Long reviewerId;

    /**
     * 审核时间
     */
    @TableField("review_time")
    private Date reviewTime;
}