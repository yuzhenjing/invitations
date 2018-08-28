package com.love.model.from;

import lombok.Data;

@Data
public class UserInfoForm {
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String city;
    private String country;
    private String province;
    private String appid;

}
