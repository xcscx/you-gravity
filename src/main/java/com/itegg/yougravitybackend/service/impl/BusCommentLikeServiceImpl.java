package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.entity.BusCommentLike;
import com.itegg.yougravitybackend.service.BusCommentLikeService;
import com.itegg.yougravitybackend.mapper.BusCommentLikeMapper;
import org.springframework.stereotype.Service;

/**
* @author ITegg
* @description 针对表【bus_comment_like(点赞表)】的数据库操作Service实现
* @createDate 2025-04-21 15:05:05
*/
@Service
public class BusCommentLikeServiceImpl extends ServiceImpl<BusCommentLikeMapper, BusCommentLike>
    implements BusCommentLikeService{

    @Override
    public boolean hasLike(Long commentId, Long userId) {
        if(ObjectUtil.isNull(commentId) || ObjectUtil.isNull(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "点赞参数异常");
        }
        QueryWrapper<BusCommentLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        queryWrapper.eq("user_id", userId);
        return this.baseMapper.selectCount(queryWrapper) > 0;
    }

}




