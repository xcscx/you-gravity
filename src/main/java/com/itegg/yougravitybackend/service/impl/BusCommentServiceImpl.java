package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.busComment.CommentAddRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentQueryRequest;
import com.itegg.yougravitybackend.model.dto.busComment.CommentReplyRequest;
import com.itegg.yougravitybackend.model.entity.BusComment;
import com.itegg.yougravitybackend.model.entity.BusCommentLike;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.vo.BusCommentVO;
import com.itegg.yougravitybackend.service.BusCommentLikeService;
import com.itegg.yougravitybackend.service.BusCommentService;
import com.itegg.yougravitybackend.mapper.BusCommentMapper;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author ITegg
* @description 针对表【bus_comment(评论表)】的数据库操作Service实现
* @createDate 2025-04-21 15:04:59
*/
@Service
@RequiredArgsConstructor
public class BusCommentServiceImpl extends ServiceImpl<BusCommentMapper, BusComment>
    implements BusCommentService{

    @Resource
    private UserService userService;

    @Resource
    @Lazy
    private BusCommentLikeService busCommentLikeService;

    private final TransactionTemplate transactionTemplate;

    @Override
    public long addComment(CommentAddRequest req) {
        // 校验数据
        if(ObjectUtil.isNull(req.getEventLocationLd()) || ObjectUtil.isNull(req.getMessage())) {
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
        if(ObjectUtil.isNull(req.getEventLocationLd()) || ObjectUtil.isNull(req.getMessage())) {
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
    public Page<BusCommentVO> getCommentList(CommentQueryRequest comReq, HttpServletRequest req) {
        // 校验数据
        if(ObjectUtil.isNull(comReq.getEventLocationLd()) || comReq.getEventLocationLd() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无法找到评论地址");
        }
        User loginUser = userService.getLoginUser(req);
        // 构建查询
        QueryWrapper<BusComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location_id", comReq.getEventLocationLd());
        queryWrapper.orderBy(StrUtil.isNotEmpty(comReq.getSortField()), comReq.getSortOrder().equals("asc"), comReq.getSortField());

        long current = comReq.getCurrent();
        long pageSize = comReq.getPageSize();
        Page<BusComment> commentPage = this.page(new Page<>(current, pageSize), queryWrapper);
        List<BusCommentVO> commentVOList = this.getCommentVOList(commentPage.getRecords(), loginUser);
        Page<BusCommentVO> commentVOPage = new Page<>(current, pageSize, commentPage.getTotal());
        commentVOPage.setRecords(commentVOList);
        return commentVOPage;
    }

    @Override
    public Long like(Long id, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        BusCommentLike req = new BusCommentLike();
        req.setCommentId(id);
        req.setUserId(loginUser.getId());
        busCommentLikeService.save(req);

        synchronized (loginUser.getId().toString().intern()) {
            // 编程式事务,评论点赞数+1
            return transactionTemplate.execute(status -> {
                BusComment comment = this.getById(id);
                boolean exists = busCommentLikeService.lambdaQuery()
                                .eq(BusCommentLike::getCommentId, id)
                                        .eq(BusCommentLike::getUserId, loginUser.getId())
                                                .exists();
                if(exists) {
                    throw new RuntimeException("用户已经点过赞了");
                }

                comment.setLikeCount(comment.getLikeCount() + 1);
                this.updateById(comment);
                return req.getId();
            });
        }
    }



    // 获取列表和点赞信息 - 重置列表接口数据
    private List<BusCommentVO> getCommentVOList(List<BusComment> commentList, User loginUser) {
        return commentList.stream().map(comment -> {
            BusCommentVO vo = new BusCommentVO();
            BeanUtils.copyProperties(vo, comment);
            if(loginUser != null) {
                vo.setLike(busCommentLikeService.hasLike(comment.getId(), loginUser.getId()));
            }
            return vo;
        }).collect(Collectors.toList());
    }

}




