package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.util.ResultUtils;
import com.itegg.yougravitybackend.common.util.ThrowUtils;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageVO;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryImageQueryRequest;
import com.itegg.yougravitybackend.service.gallery.GalleryImageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 图库图片 Controller层
 * @author ITegg
 */
@Slf4j
@RestController
@RequestMapping("/gallery/image")
public class GalleryImageController {

    @Resource
    private GalleryImageService galleryImageService;

    /**
     * 新增图库
     * @param param 新增参数
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody GalleryImageAddRequest param) {
        log.info("=========> /gallery/image/add param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryImageService.add(param));
    }

    /**
     * 查看图片
     * @param id 数据id
     * @return 查询结果
     */
    @PostMapping("/info")
    public Result<GalleryImageVO> info(@RequestParam long id) {
        log.info("=========> /gallery/image/info id={}", id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryImageService.info(id));
    }

    /**
     * 删除图片
     * @param id 数据id
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestParam long id) {
        log.info("=========> /gallery/image/delete id={}", id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryImageService.delete(id));
    }

    /**
     * 查看图片列表
     * @param param 查询参数
     * @return 查询结果
     */
    @PostMapping("/query")
    public Result<Page<GalleryImageVO>> query(@RequestBody GalleryImageQueryRequest param) {
        log.info("=========> /gallery/image/query param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
//        return ResultUtils.ok(galleryImageService.qurey(param));
        return null;
    }

}
