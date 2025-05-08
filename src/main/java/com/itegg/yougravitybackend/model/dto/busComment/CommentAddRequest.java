package com.itegg.yougravitybackend.model.dto.busComment;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动地点id
     */
    private long eventLocationLd;

    /**
     * 评论信息
     */
    private String message;

}
