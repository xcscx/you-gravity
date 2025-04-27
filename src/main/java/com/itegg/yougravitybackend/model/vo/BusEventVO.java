package com.itegg.yougravitybackend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BusEventVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 活动名称
     */
    private String eventName;

    /**
     * 活动地点
     */
    private String eventCity;

    /**
     * 开始时间
     */
    private Date eventStartTime;

    /**
     * 结束时间
     */
    private Date eventEndTime;

    /**
     * 活动背景图片
     */
    private String eventBackgroundImage;

    /**
     * 创建时间
     */
    private Date createTime;

}
