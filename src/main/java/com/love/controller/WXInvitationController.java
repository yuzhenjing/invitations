package com.love.controller;

import com.github.pagehelper.PageInfo;
import com.love.model.dao.LeaveWordDAO;
import com.love.model.dao.QueryWishDAO;
import com.love.model.from.FriendWishFrom;
import com.love.model.from.LeaveWordForm;
import com.love.model.from.QueryFriendWish;
import com.love.model.from.QueryLeaveWord;
import com.love.model.pojo.MarriageInfo;
import com.love.model.pojo.SlideImage;
import com.love.service.FriendWishService;
import com.love.service.LeaveWordService;
import com.love.service.MarriageInfoService;
import com.love.service.SlideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 于振京
 */
@Slf4j
@RestController
public class WXInvitationController {

    @Resource
    private MarriageInfoService marriageInfoService;
    @Resource
    private SlideService slideService;
    @Resource
    private FriendWishService friendWishService;
    @Resource
    private LeaveWordService leaveWordService;


    @RequestMapping("/index")
    public Map<String, Object> index(String appid) {
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(appid);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }

    @RequestMapping("/slideImage")
    public Map<String, Object> slideImage(String appid) {
        final List<SlideImage> slideImages = slideService.querySlideImages(appid);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("slideList", slideImages);
        return resMap;
    }

    @RequestMapping("/queryFriendWish")
    public Map<String, Object> queryFriendWish(@RequestBody QueryFriendWish queryFriendWish) {
        PageInfo<QueryWishDAO> friendWishList = friendWishService.queryFriendWish(queryFriendWish);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("friendWishList", friendWishList.getList());
        resMap.put("wishCount", friendWishList.getTotal());
        return resMap;
    }

    @RequestMapping("/sendBlessing")
    public Map<String, Object> sendBlessing(@RequestBody FriendWishFrom friendWish) {
        PageInfo<QueryWishDAO> friendWishList = friendWishService.sendBlessing(friendWish);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("friendWishList", friendWishList.getList());
        resMap.put("wishCount", friendWishList.getTotal());
        resMap.put("success", true);
        resMap.put("msg", "祝福已传达！");
        return resMap;
    }

    @RequestMapping("/queryLeaveWord")
    public Map<String, Object> queryLeaveWord(@RequestBody QueryLeaveWord queryLeaveWord) {
        PageInfo<LeaveWordDAO> leaveWords = leaveWordService.queryLeaveWord(queryLeaveWord);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("leaveWords", leaveWords.getList());
        resMap.put("msgCount", leaveWords.getTotal());
        return resMap;
    }

    @RequestMapping("/sendMessage")
    public Map<String, Object> sendMessage(@RequestBody LeaveWordForm leaveWordForm) {
        PageInfo<LeaveWordDAO> leaveWords = leaveWordService.sendMessage(leaveWordForm);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("leaveWords", leaveWords.getList());
        resMap.put("msgCount", leaveWords.getTotal());
        resMap.put("success", true);
        resMap.put("msg", "留言发送成功！");
        return resMap;
    }
}
