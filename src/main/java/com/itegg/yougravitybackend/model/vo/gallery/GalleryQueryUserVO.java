package com.itegg.yougravitybackend.model.vo.gallery;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 图库成员信息 VO类
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GalleryQueryUserVO extends PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
