package com.love.service.impl;

import com.love.mapper.SlideImageMapper;
import com.love.model.pojo.SlideImage;
import com.love.service.SlideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {

    @Resource
    private SlideImageMapper slideImageMapper;

    @Override
    public List<SlideImage> querySlideImages(String appid) {
        return slideImageMapper.querySlideImageByAppId(appid);
    }
}
