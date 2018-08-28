package com.love.service.impl;

import com.love.mapper.WxUserMapper;
import com.love.model.WxUser;
import com.love.service.WxUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Resource
    private WxUserMapper userMapper;

    @Override
    public WxUser queryWxUserByOpenId(String openId) {
        return userMapper.queryWxUserByOpenId(openId);
    }

    @Override
    public void insert(WxUser wxUser) {
        userMapper.insert(wxUser);
    }
}
