package com.itegg.yougravitybackend.model.vo;

import lombok.Data;

@Data
public class BusCommentVO {

    /**
     * 活动地点id
     */
    private Long eventLocationLd;

    /**
     * 评论信息
     */
    private String message;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 回复的评论，默认为0
     */
    private Long parentId;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * ---- 关联字段 ----
     */

    /**
     * 是否点赞
     */
    private boolean isLike;

}
