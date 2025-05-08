package com.itegg.yougravitybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itegg.yougravitybackend.model.dto.picture.PictureQueryRequest;
import com.itegg.yougravitybackend.model.dto.picture.PictureReviewRequest;
import com.itegg.yougravitybackend.model.dto.picture.PictureUploadRequest;
import com.itegg.yougravitybackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.vo.PictureVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片模块 service层
 * @author xtx
 */
public interface PictureService extends IService<Picture> {

    /**
     * 数据校验接口
     * @param picture 图片信息
     */
    void validPicture(Picture picture);

    /**
     * 上传图片
     * @param multipartFile 上传图片文件
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser 登录用户
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile, PictureUploadRequest pictureUploadRequest, User loginUser);

    /**
     * 图片vo类转换 - 单条
     * @param picture 图片实体类
     * @param request 请求信息
     * @return vo对象
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 图片vo类转换 - 多条
     * @param picturePage 图片实体分页类
     * @param request 请求信息
     * @return vo对象
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 查询图片
     * @param pictureQueryRequest 查询条件
     * @return 分页查询结果
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 图片审核
     * @param pictureReviewRequest 审核请求
     * @param loginUser 审核员
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    /**
     * 填充审核参数
     * @param picture 图片信息
     * @param loginUser 登录用户
     */
    void fillReviewParams(Picture picture, User loginUser);
}