package com.itegg.yougravitybackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.constant.UserConstant;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.dto.busComment.CommentAddRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentQueryRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentReplyRequest;
import com.itegg.yougravitybackend.model.entity.BusComment;
import com.itegg.yougravitybackend.service.BusCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论 控制器
 * @author itegg
 */
@RestController
@RequestMapping("/comment")
public class BusCommentController {

    @Resource
    private BusCommentService busCommentService;

    /**
     * 新增评论
     * @param request 新增评论请求参数
     * @return 返回评论id
     */
    @PostMapping("/add")
    public Result<Long> addComment(CommentAddRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busCommentService.addComment(request));
    }

    /**
     * 回复评论
     * @param request 回复评论信息
     * @return 评论id
     */
    @PostMapping("/reply")
    public Result<Long> replyComment(CommentReplyRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(busCommentService.replyComment(request));
    }

    /**
     * (仅管理或本人)删除评论
     * @param id 评论id
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteComment(IdCondition id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(!busCommentService.getById(id.getId()).getCreateBy()
                        .equals(id.getId()), ErrorCode.NO_AUTH_ERROR);
        return ResultUtils.ok(busCommentService.removeById(id.getId()));
    }

    /**
     * 获取评论列表
     * @param request 列表请求参数
     * @return 评论列表
     */
    @PostMapping("/list")
    public Result<Page<BusComment>> getCommentList(CommentQueryRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<BusComment> commentPage = busCommentService.page(new Page<>(current, pageSize),
                busCommentService.getCommentList(request));
        return ResultUtils.ok(commentPage);
    }

}
