package com.love.service.impl;

import com.love.mapper.MarriageInfoMapper;
import com.love.model.pojo.MarriageInfo;
import com.love.service.MarriageInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Service
public class MarriageInfoServiceImpl implements MarriageInfoService {

    @Resource
    private MarriageInfoMapper marriageInfoMapper;


    @Override
    public MarriageInfo queryMarriageInfo(String appid) {
        return marriageInfoMapper.queryMarriageInfo(appid);
    }
}
