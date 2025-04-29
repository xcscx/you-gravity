package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.busComment.CommentAddRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentQueryRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentReplyRequest;
import com.itegg.yougravitybackend.model.entity.BusComment;
import com.itegg.yougravitybackend.service.BusCommentService;
import com.itegg.yougravitybackend.mapper.BusCommentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_comment(评论表)】的数据库操作Service实现
* @createDate 2025-04-21 15:04:59
*/
@Service
public class BusCommentServiceImpl extends ServiceImpl<BusCommentMapper, BusComment>
    implements BusCommentService{

    @Override
    public long addComment(CommentAddRequest req) {
        // 校验数据
        if(ObjectUtil.isNull(req.getLocationId()) || ObjectUtil.isNull(req.getMessage())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评论信息不完整");
        }
        // 添加数据
        BusComment comment = new BusComment();
        BeanUtils.copyProperties(comment, req);
        boolean saveResult = this.save(comment);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常，评论添加失败");
        }
        return comment.getId();
    }

    @Override
    public long replyComment(CommentReplyRequest req) {
        // 校验数据
        if(ObjectUtil.isNull(req.getLocationId()) || ObjectUtil.isNull(req.getMessage())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评论信息不完整");
        }
        // 校验父评论
        if(ObjectUtil.isNull(req.getParentId()) || req.getParentId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "父信息有误");
        }
        BusComment parentComment = this.getById(req.getParentId());
        if(ObjectUtil.isNull(parentComment)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "找不到父信息");
        }
        // 添加数据
        BusComment comment = new BusComment();
        BeanUtils.copyProperties(comment, req);
        boolean saveResult = this.save(comment);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统异常，评论添加失败");
        }
        return comment.getId();
    }

    @Override
    public QueryWrapper<BusComment> getCommentList(CommentQueryRequest req) {
        // 校验数据
        if(ObjectUtil.isNull(req.getLocationId()) || req.getLocationId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无法找到评论地址");
        }
        // 构建查询
        QueryWrapper<BusComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location_id", req.getLocationId());
        queryWrapper.orderBy(StrUtil.isNotEmpty(req.getSortField()), req.getSortOrder().equals("asc"), req.getSortField());
        return queryWrapper;
    }

}




