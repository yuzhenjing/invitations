package com.love.service;

import com.github.pagehelper.PageInfo;
import com.love.model.dao.LeaveWordDAO;
import com.love.model.from.LeaveWordForm;
import com.love.model.from.QueryLeaveWord;

public interface LeaveWordService {
    /**
     * 查询留言
     *
     * @param queryLeaveWord
     * @return
     */
    PageInfo<LeaveWordDAO> queryLeaveWord(QueryLeaveWord queryLeaveWord);

    /**
     * 留言
     *
     * @param leaveWordForm
     * @return
     */
    PageInfo<LeaveWordDAO> sendMessage(LeaveWordForm leaveWordForm);
}
