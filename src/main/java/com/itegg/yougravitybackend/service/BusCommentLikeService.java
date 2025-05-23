package com.itegg.yougravitybackend.service;

import com.itegg.yougravitybackend.model.entity.BusCommentLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ITegg
* @description 针对表【bus_comment_like(点赞表)】的数据库操作Service
* @createDate 2025-04-21 15:05:05
*/
public interface BusCommentLikeService extends IService<BusCommentLike> {

    /**
     * 查看是否点赞
     * @param commentId 评论id
     * @param userId 用户id
     * @return 是否点赞
     */
    boolean hasLike(Long commentId, Long userId);

}
