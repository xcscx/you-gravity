package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryMapper;
import com.itegg.yougravitybackend.model.entity.gallery.Gallery;
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


}
