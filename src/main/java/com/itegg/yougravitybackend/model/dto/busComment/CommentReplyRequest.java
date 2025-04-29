package com.itegg.yougravitybackend.model.dto.busComment;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentReplyRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地点id
     */
    private long locationId;

    /**
     * 评论信息
     */
    private String message;

    /**
     * 父级评论id
     */
    private long parentId;

}
