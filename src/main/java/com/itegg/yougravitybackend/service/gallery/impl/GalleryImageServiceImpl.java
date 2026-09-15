package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryImageMapper;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryImage;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageVO;
import com.itegg.yougravitybackend.service.gallery.GalleryImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图库Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class GalleryImageServiceImpl extends ServiceImpl<GalleryImageMapper, GalleryImage>
        implements GalleryImageService {

    @Override
    public boolean add(GalleryImageAddRequest param) {
        return true;
    }

    @Override
    public GalleryImageVO info(long id) {
        return null;
    }

    @Override
    public GalleryImageVO query(GalleryImageQueryRequest param) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return true;
    }

}
