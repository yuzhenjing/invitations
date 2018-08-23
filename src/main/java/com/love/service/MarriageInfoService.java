package com.love.service;

import com.love.model.pojo.MarriageInfo;

public interface MarriageInfoService {

    /**
     * 查询邀请函基本信息
     *
     * @param appid
     * @return
     */
    MarriageInfo queryMarriageInfo(String appid);
}
