package com.love.model.from;

import lombok.Data;

import java.util.Date;

@Data
public class FriendWishFrom {

    private Integer gender;

    private String appId;

    private String avatarUrl;

    private String nickName;

    private String country;
    
    private String province;

    private String city;

    private Integer startNum;
}
