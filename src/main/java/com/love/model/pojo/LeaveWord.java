package com.love.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LeaveWord {
    private Integer chatId;

    private String appId;

    private String nickName;

    private String avatarUrl;

    private String message;

    private Date createTime;
}
