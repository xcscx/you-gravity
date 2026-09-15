package com.itegg.yougravitybackend.service.gallery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.gallery.Gallery;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryUpdateRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryVO;

/**
 * 图库 Service层
 * @author ITegg
 */
public interface GalleryService extends IService<Gallery> {

    /**
     * 添加图库
     * @param param 添加参数
     * @return 添加结果
     */
    boolean add(GalleryAddRequest param);

    /**
     * 查询图库
     * @param param 查询参数
     * @return 查询结果
     */
    GalleryVO query(GalleryQueryRequest param);

    /**
     * 图库详情
     * @param id 数据id
     * @return 查询结果
     */
    GalleryVO info(long id);

    /**
     * 修改图库
     * @param param 修改参数
     * @return 修改结果
     */
    boolean updateGallery(GalleryUpdateRequest param);

    /**
     * 删除图库
     * @param id 数据id
     * @return 删除结果
     */
    boolean delete(long id);

}