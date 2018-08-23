package com.love.mapper;

import com.love.model.dao.LeaveWordDAO;
import com.love.model.from.LeaveWordForm;

import java.util.List;

/**
 * @author admin
 */
public interface LeaveWordMapper {
    /**
     * 查询留言信息
     *
     * @param appId
     * @return
     */
    List<LeaveWordDAO> queryLeaveWordsByAppId(String appId);

    /**
     * 添加留言
     *
     * @param leaveWordForm
     * @return
     */
    int sendMessage(LeaveWordForm leaveWordForm);
}
