package com.love.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.love.mapper.FriendWishMapper;
import com.love.model.dao.QueryWishDAO;
import com.love.model.from.FriendWishFrom;
import com.love.model.from.QueryFriendWish;
import com.love.service.FriendWishService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Service
public class FriendWishServiceImpl implements FriendWishService {
    @Resource
    private FriendWishMapper friendWishMapper;

    @Override
    public PageInfo<QueryWishDAO> queryFriendWish(QueryFriendWish queryFriendWish) {

//        PageHelper.startPage(queryFriendWish.getStartNum(), 40);
        List<QueryWishDAO> friendWishList = friendWishMapper.queryFriendWish(queryFriendWish.getAppId());
        PageInfo<QueryWishDAO> pageInfo = new PageInfo<>(friendWishList);
        return pageInfo;
    }

    @Override
    public PageInfo<QueryWishDAO> sendBlessing(FriendWishFrom friendWish) {

        List<QueryWishDAO> wishs = friendWishMapper.queryFriendWishByNickName(friendWish.getNickName());
        if (CollectionUtils.isEmpty(wishs)) {
            friendWishMapper.sendBlessing(friendWish);
        }
        QueryFriendWish queryFriendWish = new QueryFriendWish();
        queryFriendWish.setAppId(friendWish.getAppId());
        queryFriendWish.setStartNum(friendWish.getStartNum());
        return queryFriendWish(queryFriendWish);
    }
}
