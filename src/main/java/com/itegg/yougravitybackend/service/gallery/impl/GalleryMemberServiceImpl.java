package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryMemberMapper;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryMember;
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


}
