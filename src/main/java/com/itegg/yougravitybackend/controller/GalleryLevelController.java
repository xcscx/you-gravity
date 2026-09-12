package com.itegg.yougravitybackend.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.util.ResultUtils;
import com.itegg.yougravitybackend.common.util.ThrowUtils;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.gallery.*;
import com.itegg.yougravitybackend.service.gallery.GalleryLevelService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 图库等级 Controller层
 * @author ITegg
 */
@Slf4j
@RestController
@RequestMapping("/gallery/level")
public class GalleryLevelController {

    @Resource
    private GalleryLevelService galleryLevelService;

    /**
     * 新增图库等级
     * @param param 新增参数
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<Boolean> addLevel(@RequestBody GalleryLevelAddRequest param) {
        log.info("=========> /gallery/level/add param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryLevelService.addLevel(param));
    }

    /**
     * 查看图库等级
     * @param id 数据id
     * @return 查询结果
     */
    @GetMapping("/info")
    public Result<GalleryLevelVO> levelInfo(@RequestParam long id) {
        log.info("=========> /gallery/level/info id={}", JSONUtil.toJsonStr(id));
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryLevelService.levelInfo(id));
    }

    /**
     * 修改图库等级
     * @param param 修改参数
     * @return 添加结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody GalleryLevelUpdateRequest param) {
        log.info("=========> /gallery/level/update param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryLevelService.updateLevel(param));
    }

    /**
     * 删除图库等级
     * @param id 数据id
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestParam long id) {
        log.info("=========> /gallery/level/delete id={}", JSONUtil.toJsonStr(id));
        ThrowUtils.throwIf(ObjectUtil.isEmpty(id), ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryLevelService.delete(id));
    }

    /**
     * 图库等级列表
     * @param param 查询参数
     * @return 查询结果
     */
    @PostMapping("/list")
    public Result<GalleryLevelListVO> levelList(@RequestBody GalleryLevelListRequest param) {
        log.info("=========> /gallery/level/list param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(galleryLevelService.levelList(param));
    }

}
