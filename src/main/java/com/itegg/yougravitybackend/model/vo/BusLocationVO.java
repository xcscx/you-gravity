package com.itegg.yougravitybackend.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BusLocationVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 地点名称
     */
    private String locationName;

    /**
     * 想去人数
     */
    private Integer wantGoCount;

    /**
     * 是否想去
     */
    private Boolean isWantGo;

    /**
     * 是否打卡
     */
    private  Boolean isCheckIn;

    /**
     * 图片
     */
    private List<AttachmentVO> pictures;

}
