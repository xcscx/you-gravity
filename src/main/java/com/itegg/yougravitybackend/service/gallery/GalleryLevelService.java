package com.itegg.yougravitybackend.service.gallery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryLevel;
import com.itegg.yougravitybackend.model.vo.gallery.*;

/**
 * 图库等级 Service层
 * @author ITegg
 */
public interface GalleryLevelService extends IService<GalleryLevel> {

    /**
     * 添加新等级
     * @param param 新增参数
     * @return 添加结果
     */
    boolean addLevel(GalleryLevelAddRequest param);

    /**
     * 查询等级详情
     * @param id 数据id
     * @return 详情信息
     */
    GalleryLevelVO levelInfo(long id);

    /**
     * 更新等级信息
     * @param param 更新参数
     * @return 更新结果
     */
    boolean updateLevel(GalleryLevelUpdateRequest param);

    /**
     * 删除等级
     * @param id 数据id
     * @return 删除结果
     */
    boolean delete(long id);

    /**
     * 图库等级上移
     * @param param 上移参数
     * @return 上移结果
     */
    boolean LevelUp(GalleryLevelUpRequest param);

    /**
     * 图库列表搜索
     * @param param 搜索参数
     * @return 查询结果
     */
    GalleryLevelListVO levelList(GalleryLevelListRequest param);

}