package com.itegg.yougravitybackend.service.gallery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryImage;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageVO;

/**
 * 图库图片 Service层
 * @author ITegg
 */
public interface GalleryImageService extends IService<GalleryImage> {

    boolean add(GalleryImageAddRequest param);

    GalleryImageVO info(long id);

    GalleryImageVO query(GalleryImageQueryRequest param);

    boolean delete(long id);
}