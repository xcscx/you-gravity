package com.itegg.yougravitybackend.service.gallery.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.gallery.GalleryLevelMapper;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryLevel;
import com.itegg.yougravitybackend.model.vo.gallery.*;
import com.itegg.yougravitybackend.service.gallery.GalleryLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 图库等级Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class GalleryLevelServiceImpl extends ServiceImpl<GalleryLevelMapper, GalleryLevel>
        implements GalleryLevelService {

    @Override
    public boolean addLevel(GalleryLevelAddRequest param) {
        // 校验权限

        // 校验参数

        // 添加等级

        return true;
    }

    @Override
    public GalleryLevelVO levelInfo(long id) {
        // 校验参数

        // 查询信息

        return null;
    }

    @Override
    public boolean updateLevel(GalleryLevelUpdateRequest param) {
        // 校验权限

        // 校验参数

        // 更新信息

        return true;
    }

    @Override
    public boolean delete(long id) {
        // 校验权限

        // 校验参数

        // 查询关联图库

        // 更改关联图库等级

        // 删除数据

        return true;
    }

    @Override
    public boolean LevelUp(GalleryLevelUpRequest param) {
        // 权限校验

        // 参数校验

        // 等级更改

         return true;
    }

    @Override
    public GalleryLevelListVO levelList(GalleryLevelListRequest param) {
        // 查询数据

        return null;
    }

}
