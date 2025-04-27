package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.itegg.yougravitybackend.common.model.IdModel;
import lombok.Data;

/**
 * 点赞表
 * @TableName bus_comment_like
 */
@TableName(value ="bus_comment_like")
@Data
public class BusCommentLike extends IdModel {

    /**
     * 点赞用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 点赞评论
     */
    @TableField("comment_id")
    private Long commentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}