package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryMemberMapper;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryMember;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryDeleteRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryJoinRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryUserVO;
import com.itegg.yougravitybackend.service.gallery.GalleryMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图库成员Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class GalleryMemberServiceImpl extends ServiceImpl<GalleryMemberMapper, GalleryMember>
        implements GalleryMemberService {

    @Override
    public boolean joinGallery(GalleryJoinRequest param) {
        // 校验参数

        // 添加成员

        return true;
    }

    @Override
    public GalleryQueryUserVO queryUser(GalleryQueryRequest param) {
        // 校验参数

        // 关联表查询

        // 用户模块查询

        return null;
    }

    @Override
    public boolean delete(GalleryDeleteRequest param) {
        // 校验参数

        // 处理图库图片信息

        // 删除图库成员

        return true;
    }

}
