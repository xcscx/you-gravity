package com.itegg.yougravitybackend.service.gallery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.gallery.GalleryMember;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryDeleteRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryJoinRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryQueryRequest;
import com.itegg.yougravitybackend.model.vo.gallery.GalleryMemberQueryVO;

/**
 * 图库成员 Service层
 * @author ITegg
 */
public interface GalleryMemberService extends IService<GalleryMember> {

    /**
     * 加入图库
     * @param param 加入图库参数
     * @return 加入结果
     */
    boolean joinGallery(GalleryJoinRequest param);

    /**
     * 查询用户加入的图库
     * @param param 查询参数
     * @return 查询结果
     */
    GalleryMemberQueryVO queryUser(GalleryQueryRequest param);

    /**
     * 删除图库成员
     * @param param 删除参数
     * @return 删除结果
     */
    boolean delete(GalleryDeleteRequest param);

}