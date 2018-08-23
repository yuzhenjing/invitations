package com.love.mapper;

import com.love.model.pojo.SlideImage;

import java.util.List;

public interface SlideImageMapper {
    /**
     * @param appid
     * @return
     */
    List<SlideImage> querySlideImageByAppId(String appid);
}
