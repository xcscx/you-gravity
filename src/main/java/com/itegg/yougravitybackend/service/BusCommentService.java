package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.model.dto.busComment.CommentAddRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentQueryRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentReplyRequest;
import com.itegg.yougravitybackend.model.entity.BusComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.BusCommentVO;
import jakarta.servlet.http.HttpServletRequest;

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
    Page<BusCommentVO> getCommentList(CommentQueryRequest commentRequest, HttpServletRequest request);

    /**
     * 点赞
     * @param id 评论id
     * @return 点赞结果
     */
    Long like(Long id, HttpServletRequest request);
}
