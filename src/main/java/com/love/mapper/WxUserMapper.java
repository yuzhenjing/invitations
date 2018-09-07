package com.love.mapper;

import com.love.model.WxUser;

public interface WxUserMapper {

    int insert(WxUser record);

    WxUser queryWxUserByOpenId(String openId);
}
