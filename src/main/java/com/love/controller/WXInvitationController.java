package com.love.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.love.model.WxUser;
import com.love.model.dao.LeaveWordDAO;
import com.love.model.dao.QueryWishDAO;
import com.love.model.from.*;
import com.love.model.pojo.MarriageInfo;
import com.love.model.pojo.SlideImage;
import com.love.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 于振京
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class WXInvitationController {

    @Resource
    private MarriageInfoService marriageInfoService;
    @Resource
    private SlideService slideService;
    @Resource
    private FriendWishService friendWishService;
    @Resource
    private LeaveWordService leaveWordService;
    @Resource
    private WxUserService userService;

    @Value("${wexin.appid}")
    private String appid;
    @Value("${wexin.secret}")
    private String secret;
    @Value("${wexin.url}")
    private String url;


    @RequestMapping("/index")
    public Map<String, Object> index(UserInfoForm userInfoForm) {
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(appid);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String message = restTemplate.getForEntity(url, String.class).getBody();
        log.info("微信端返回数据是：{}", message);
        message = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
        JSONObject jsonObject = JSONObject.parseObject(message);
        WxUser user = userService.queryWxUserByOpenId(jsonObject.getString("openid"));
        if (user == null) {
            user = new WxUser();
            user.setAddTime(new Date());
            user.setNickName("");
            user.setSessionKey(jsonObject.getString("session_key"));
            user.setOpenId(jsonObject.getString("openid"));
            userService.insert(user);
        }
        Map<String, Object> resMap = new HashMap<>();
        return resMap;
    }

    @RequestMapping("/slideImage")
    public Map<String, Object> slideImage(String appid) {
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(appid);
        final List<SlideImage> slideImages = slideService.querySlideImages(appid);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("slideList", slideImages);
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }

    @RequestMapping("/queryFriendWish")
    public Map<String, Object> queryFriendWish(@RequestBody QueryFriendWish queryFriendWish) {
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(queryFriendWish.getAppId());
        PageInfo<QueryWishDAO> friendWishList = friendWishService.queryFriendWish(queryFriendWish);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("friendWishList", friendWishList.getList());
        resMap.put("wishCount", friendWishList.getTotal());
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }

    @RequestMapping("/sendBlessing")
    public Map<String, Object> sendBlessing(@RequestBody FriendWishFrom friendWish) {
        PageInfo<QueryWishDAO> friendWishList = friendWishService.sendBlessing(friendWish);
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(friendWish.getAppId());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("friendWishList", friendWishList.getList());
        resMap.put("wishCount", friendWishList.getTotal());
        resMap.put("success", true);
        resMap.put("mainInfo", marriageInfo);
        resMap.put("msg", "新人收到您的祝福啦！");
        return resMap;
    }

    @RequestMapping("/queryLeaveWord")
    public Map<String, Object> queryLeaveWord(@RequestBody QueryLeaveWord queryLeaveWord) {
        PageInfo<LeaveWordDAO> leaveWords = leaveWordService.queryLeaveWord(queryLeaveWord);
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(queryLeaveWord.getAppId());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("leaveWords", leaveWords.getList());
        resMap.put("msgCount", leaveWords.getTotal());
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }

    @RequestMapping("/sendMessage")
    public Map<String, Object> sendMessage(@RequestBody LeaveWordForm leaveWordForm) {
        PageInfo<LeaveWordDAO> leaveWords = leaveWordService.sendMessage(leaveWordForm);
        final MarriageInfo marriageInfo = marriageInfoService.queryMarriageInfo(leaveWordForm.getAppId());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("leaveWords", leaveWords.getList());
        resMap.put("msgCount", leaveWords.getTotal());
        resMap.put("success", true);
        resMap.put("msg", "留言发送成功！");
        resMap.put("mainInfo", marriageInfo);
        return resMap;
    }
}
