package com.itegg.yougravitybackend.controller;

import cn.hutool.json.JSONUtil;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.util.ResultUtils;
import com.itegg.yougravitybackend.common.util.ThrowUtils;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryDeleteRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryJoinRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryUserVO;
import com.itegg.yougravitybackend.service.gallery.GalleryMemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图库成员 Controller层
 * @author ITegg
 */
@Slf4j
@RestController
@RequestMapping("/gallery/member")
public class GalleryMemberController {

    @Resource
    private GalleryMemberService galleryMemberService;

    /**
     * 新增成员
     * @param param 新增成员参数
     * @return 添加结果
     */
    @PostMapping("/join")
    public Result<Boolean> joinGallery(@RequestBody GalleryJoinRequest param) {
        log.info("=========> /gallery/member/join param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null || param.getGalleryId() == null || param.getUserId() == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryMemberService.joinGallery(param));
    }

    /**
     * 查询成员
     * @param param 查询参数
     * @return 查询结果
     */
    @PostMapping("/qurey")
    public Result<GalleryQueryUserVO> qurey(@RequestBody GalleryQueryRequest param) {
        log.info("=========> /gallery/member/join param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null || param.getGalleryId() == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryMemberService.queryUser(param));
    }

    /**
     * 删除成员
     * @param param 删除参数
     * @return 删除结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody GalleryDeleteRequest param) {
        log.info("=========> /gallery/member/join param={}", JSONUtil.toJsonStr(param));
        ThrowUtils.throwIf(param == null || param.getGalleryId() == null || param.getUserId() == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.ok(galleryMemberService.delete(param));
    }

}
