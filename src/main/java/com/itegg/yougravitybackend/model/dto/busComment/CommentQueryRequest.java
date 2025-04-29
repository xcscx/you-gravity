package com.itegg.yougravitybackend.model.dto.busComment;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentQueryRequest  extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地点id
     */
    private long locationId;

}
