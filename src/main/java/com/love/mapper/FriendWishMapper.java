package com.love.mapper;

import com.love.model.dao.QueryWishDAO;
import com.love.model.from.FriendWishFrom;

import java.util.List;

public interface FriendWishMapper {


    List<QueryWishDAO> queryFriendWish(String appid);

    int sendBlessing(FriendWishFrom friendWish);

    List<QueryWishDAO> queryFriendWishByNickName(String nickName);

}
