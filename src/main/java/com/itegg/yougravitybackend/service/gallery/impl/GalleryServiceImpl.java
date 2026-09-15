package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryMapper;
import com.itegg.yougravitybackend.model.entity.gallery.Gallery;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryUpdateRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryVO;
import com.itegg.yougravitybackend.service.gallery.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图库Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class GalleryServiceImpl extends ServiceImpl<GalleryMapper, Gallery>
        implements GalleryService {

    @Override
    public boolean add(GalleryAddRequest param) {
        // 权限校验

        // 参数校验

        // 新增图库

        return true;
    }

    @Override
    public GalleryVO query(GalleryQueryRequest param) {
        // 权限校验

        // 参数校验

        // 查询图库

        return null;
    }

    @Override
    public GalleryVO info(long id) {
        // 权限校验

        // 参数校验

        // 查询图库

        return null;
    }

    @Override
    public boolean updateGallery(GalleryUpdateRequest param) {
        // 权限校验

        // 参数校验

        // 图库修改

        return true;
    }

    @Override
    public boolean delete(long id) {
        // 权限校验

        // 参数校验

        // 关联图片调整

        // 图库删除

        return true;
    }

}
