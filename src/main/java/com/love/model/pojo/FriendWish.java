package com.love.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class FriendWish {
    private Integer wishId;

    private Integer gender;

    private String appId;

    private String avatarUrl;

    private String nickName;

    private String country;

    private String province;

    private String city;

    private Date wishTime;


}
