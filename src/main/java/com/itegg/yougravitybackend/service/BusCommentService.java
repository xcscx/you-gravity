package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itegg.yougravitybackend.model.dto.busComment.CommentAddRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentQueryRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentReplyRequest;
import com.itegg.yougravitybackend.model.entity.BusComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ITegg
* @description 针对表【bus_comment(评论表)】的数据库操作Service
* @createDate 2025-04-21 15:04:59
*/
public interface BusCommentService extends IService<BusComment> {

    /**
     * 添加评论
     * @param request 添加评论参数
     * @return 评论id
     */
    long addComment(CommentAddRequest request);

    /**
     * 回复评论
     * @param request 回复评论参数
     * @return 评论id
     */
    long replyComment(CommentReplyRequest request);

    /**
     * 获取评论列表
     * @param request 获取评论列表参数
     * @return 评论列表
     */
    QueryWrapper<BusComment> getCommentList(CommentQueryRequest request);

}
