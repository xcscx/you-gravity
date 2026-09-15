package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.util.ResultUtils;
import com.itegg.yougravitybackend.common.util.ThrowUtils;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryAddRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryUpdateRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryVO;
import com.itegg.yougravitybackend.service.gallery.GalleryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 图库 Controller层
 * @author ITegg
 */
@Slf4j
@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Resource
    private GalleryService galleryService;

    /**
     * 新增图库
     * @param param 新增参数
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody GalleryAddRequest param) {
        log.info("=========> /gallery/add param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryService.add(param));
    }

    /**
     * 查询用户所属的图库
     * @param param 查询参数
     * @return 查询结果
     */
    @PostMapping("/query")
    public Result<Page<GalleryVO>> query(@RequestBody GalleryQueryRequest param) {
        log.info("=========> /gallery/query param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
//        return ResultUtils.ok(galleryService.qurey(param));
        return null;
    }

    /**
     * 图库详情
     * @param id 数据id
     * @return 查询结果
     */
    @PostMapping("/info")
    public Result<GalleryVO> info(@RequestParam long id) {
        log.info("=========> /gallery/info id={}", id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryService.info(id));
    }

    /**
     * 图库更新信息
     * @param param 更新参数
     * @return 更新
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody GalleryUpdateRequest param) {
        log.info("=========> /gallery/update param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryService.updateGallery(param));
    }

    /**
     * 删除图库
     * @param id 删除id
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestParam long id) {
        log.info("=========> /gallery/delete id={}", id);
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryService.delete(id));
    }

}
