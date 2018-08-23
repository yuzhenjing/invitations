package com.love.service;

import com.love.model.pojo.SlideImage;

import java.util.List;

public interface SlideService {
    List<SlideImage> querySlideImages(String appid);
}
