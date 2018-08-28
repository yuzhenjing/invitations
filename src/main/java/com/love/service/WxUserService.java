package com.love.service;

import com.love.model.WxUser;

public interface WxUserService {

    WxUser queryWxUserByOpenId(String openId);


    void insert(WxUser wxUser);
}
