package com.love.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.love.mapper.LeaveWordMapper;
import com.love.model.dao.LeaveWordDAO;
import com.love.model.from.LeaveWordForm;
import com.love.model.from.QueryLeaveWord;
import com.love.service.LeaveWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LeaveWordServiceImpl implements LeaveWordService {

    @Resource
    private LeaveWordMapper leaveWordMapper;

    @Override
    public PageInfo<LeaveWordDAO> queryLeaveWord(QueryLeaveWord queryLeaveWord) {
//        PageHelper.startPage(queryLeaveWord.getStartNum(), 20);
        List<LeaveWordDAO> leaveWords = leaveWordMapper.queryLeaveWordsByAppId(queryLeaveWord.getAppId());
        PageInfo<LeaveWordDAO> pageInfo = new PageInfo<>(leaveWords);
        return pageInfo;
    }


    @Override
    public PageInfo<LeaveWordDAO> sendMessage(LeaveWordForm leaveWordForm) {
        leaveWordMapper.sendMessage(leaveWordForm);
        QueryLeaveWord queryLeaveWord = new QueryLeaveWord();
        queryLeaveWord.setAppId(leaveWordForm.getAppId());
        queryLeaveWord.setStartNum(0);
        return queryLeaveWord(queryLeaveWord);
    }
}
