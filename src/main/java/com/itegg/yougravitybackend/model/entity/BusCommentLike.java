package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 点赞表
 * @TableName bus_comment_like
 */
@TableName(value ="bus_comment_like")
@Data
public class BusCommentLike {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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