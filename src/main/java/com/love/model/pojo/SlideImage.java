package com.love.model.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SlideImage {
    private Integer slideId;

    private String userId;

    private String imageUrl;

    private Date createTime;
}
