package com.love.mapper;

import com.love.model.pojo.MarriageInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface MarriageInfoMapper {
    /**
     * 查询基础信息
     *
     * @param appid
     * @return
     */
    MarriageInfo queryMarriageInfo(String appid);
}
