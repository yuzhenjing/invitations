package com.love.service;

import com.github.pagehelper.PageInfo;
import com.love.model.dao.QueryWishDAO;
import com.love.model.from.FriendWishFrom;
import com.love.model.from.QueryFriendWish;
import com.love.model.pojo.FriendWish;

import java.util.List;

/**
 * @author admin
 */
public interface FriendWishService {
    /**
     * 查询祝福语
     *
     * @param queryFriendWish
     * @return
     */
    PageInfo<QueryWishDAO> queryFriendWish(QueryFriendWish queryFriendWish);

    /**
     * 送上祝福
     *
     * @param friendWish
     * @return
     */
    PageInfo<QueryWishDAO> sendBlessing(FriendWishFrom friendWish);
}
