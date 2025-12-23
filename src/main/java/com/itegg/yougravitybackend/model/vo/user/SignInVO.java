package com.itegg.yougravitybackend.model.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户签到数据
 * @author ITegg
 */
@Data
public class SignInVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 签到日期 yyyy/mm/dd
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    /**
     * 气运
     */
    private String luck;

    /**
     * 名句id
     */
    private Long famousQuoteId;

    /**
     * 名句内容
     */
    private String content;

    /**
     * 名句作者
     */
    private String author;

    /**
     * 名句出处
     */
    private String source;

}
