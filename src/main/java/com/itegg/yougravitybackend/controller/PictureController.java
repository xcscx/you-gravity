package com.itegg.yougravitybackend.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.aop.annotation.AuthCheck;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.constant.UserConstant;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.dto.picture.*;
import com.itegg.yougravitybackend.model.entity.Picture;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.enums.PictureReviewStatusEnum;
import com.itegg.yougravitybackend.model.vo.PictureTagCategory;
import com.itegg.yougravitybackend.model.vo.PictureVO;
import com.itegg.yougravitybackend.service.PictureService;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    /**
     * 上传图片（兼容重新上传）
     * @param multipartFile 文件
     * @param pictureUploadRequest 图片请求
     * @param request http请求
     * @return 上传的图片信息
     */
    @PostMapping("/upload")
    public Result<PictureVO> uploadPicture(@RequestPart("file")MultipartFile multipartFile, PictureUploadRequest pictureUploadRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);
        return ResultUtils.ok(pictureVO);
    }

    /**
     * 更新图片（仅管理员可用）
     * @param pictureUpdateRequest 图片文件
     * @return 修改结果
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> updatePicture(@RequestBody PictureUpdateRequest pictureUpdateRequest, HttpServletRequest request) {
        if(ObjectUtil.isNull(pictureUpdateRequest) || pictureUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 实体类 和 DTO进行转换
        Picture picture = new Picture();
        BeanUtils.copyProperties(pictureUpdateRequest, picture);
        // 将list属性转换为String
        picture.setTags(JSONUtil.toJsonStr(pictureUpdateRequest.getTags()));
        // 数据校验
        pictureService.validPicture(picture);
        // 判断数据是否存在
        long id = pictureUpdateRequest.getId();
        Picture oldPicture = pictureService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(oldPicture), ErrorCode.NOT_FOUND_ERROR);
        // 补充审核参数
        User loginUser = userService.getLoginUser(request);
        pictureService.fillReviewParams(picture, loginUser);
        // 操作数据库
        boolean result = pictureService.updateById(picture);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 删除图片接口
     * @param id 图片id
     * @return 删除是否成功
     */
    @PostMapping("/delete")
    public Result<Boolean> deletePicture(@RequestBody IdCondition id, HttpServletRequest request) {
        if(ObjectUtil.isNull(id) || id.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 管理员和自己都可以删除，所以不用注解区分权限
        User loginUser = userService.getLoginUser(request);
        Long picId = id.getId();
        Picture oldPicture = pictureService.getById(picId);
        ThrowUtils.throwIf(ObjectUtil.isNull(oldPicture), ErrorCode.NOT_FOUND_ERROR);
        // 权限校验
        if(!oldPicture.getCreateBy().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = pictureService.removeById(picId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 依据id获取图片 - 仅管理员使用
     * @param id 图片id
     * @param request http请求
     * @return 图片详细信息
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Picture> getPictureById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据
        Picture picture = pictureService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(picture), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.ok(picture);
    }

    /**
     * 依据id获取图片
     * @param id 图片id
     * @param request http请求
     * @return 图片脱敏信息
     */
    @GetMapping("/get/vo")
    public Result<PictureVO> getPictureVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据
        Picture picture = pictureService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(picture), ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.ok(pictureService.getPictureVO(picture, request));
    }

    /**
     * 分页获取图片列表（仅管理员可用）
     * @param request 分页查询条件
     * @return 分页结论
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest request) {
        long current = request.getCurrent();
        long size = request.getPageSize();
        // 查询数据库
        Page<Picture> picturePage = pictureService.page(new Page<>(current, size),
                pictureService.getQueryWrapper(request));
        return ResultUtils.ok(picturePage);
    }

    /**
     * 分页获取图片列表
     * @param request 分页查询条件
     * @return 脱敏分页结论
     */
    @PostMapping("/list/page/vo")
    public Result<Page<PictureVO>> listPictureVOByPage(@RequestBody PictureQueryRequest request, HttpServletRequest httpServletRequest) {
        long current = request.getCurrent();
        long size = request.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 限制普通用户只看已通过审核的数据
        request.setReviewStatus(PictureReviewStatusEnum.PASS.getValue());
        // 查询数据库
        Page<Picture> picturePage = pictureService.page(new Page<>(current, size),
                pictureService.getQueryWrapper(request));
        return ResultUtils.ok(pictureService.getPictureVOPage(picturePage, httpServletRequest));
    }

    /**
     * 编辑图片
     * @param pictureEditRequest 图片文件
     * @param request http请求
     * @return 修改结果
     */
    @PostMapping("/edit")
    public Result<Boolean> editPicture(@RequestBody PictureEditRequest pictureEditRequest, HttpServletRequest request) {
        if(ObjectUtil.isNull(pictureEditRequest) || pictureEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 实体类 和 DTO进行转换
        Picture picture = new Picture();
        BeanUtils.copyProperties(pictureEditRequest, picture);
        // 将list属性转换为String
        picture.setTags(JSONUtil.toJsonStr(pictureEditRequest.getTags()));
        // 数据校验
        pictureService.validPicture(picture);
        User loginUser = userService.getLoginUser(request);
        // 补充审核参数
        pictureService.fillReviewParams(picture, loginUser);
        // 判断数据是否存在
        long id = pictureEditRequest.getId();
        Picture oldPicture = pictureService.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(oldPicture), ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        // 权限校验
        if(!oldPicture.getCreateBy().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = pictureService.updateById(picture);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.ok(true);
    }

    /**
     * 审核图片 - 仅管理员可用
     * @param request 审核请求
     * @param httpServletRequest http请求
     * @return 是否成功审核
     */
    @PostMapping("/review")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public Result<Boolean> doPictureReview(@RequestBody PictureReviewRequest request, HttpServletRequest httpServletRequest) {
        ThrowUtils.throwIf(ObjectUtil.isNull(request), ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(httpServletRequest);
        pictureService.doPictureReview(request, loginUser);
        return ResultUtils.ok(true);
    }

    /**
     * -----  项目启动  -----
     */

    /**
     * 默认返回的分类和标签
     * @return 分类列表和标签列表
     */
    @GetMapping("/tag_category")
    public Result<PictureTagCategory> listPictureTagCategory() {
        PictureTagCategory pictureTagCategory = new PictureTagCategory();
        List<String> tagList = Arrays.asList("热门", "搞笑", "生活", "高清", "艺术", "校园", "背景", "简历", "创意");
        List<String> categoryList = Arrays.asList("模板", "电商", "表情包", "素材", "海报");
        pictureTagCategory.setTagList(tagList);
        pictureTagCategory.setCategoryList(categoryList);
        return ResultUtils.ok(pictureTagCategory);
    }

}
