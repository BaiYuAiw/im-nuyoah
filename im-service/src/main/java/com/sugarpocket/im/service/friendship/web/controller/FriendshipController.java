package com.sugarpocket.im.service.friendship.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sugarpocket.im.common.model.ResponseResult;
import com.sugarpocket.im.service.friendship.dao.resp.CheckFriendShipResp;
import com.sugarpocket.im.service.friendship.service.FriendshipService;
import com.sugarpocket.im.service.friendship.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:39
 */
@RestController("/friendship")
public class FriendshipController {
    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/importFriendship")
    public ResponseResult importFriendship(
            @RequestBody ImportFriendshipReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        JSONObject obj = friendshipService.importFriendship(req);
        return obj.getInteger("size") == req.getFriendItem().size() ?
                ResponseResult.newSuccessResult(obj) :
                ResponseResult.newFailResult(obj);
    }

    @PostMapping("/addFriend")
    public ResponseResult addFriend(
            @RequestBody AddFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return friendshipService.addFriend(req) ? ResponseResult.newSuccessResult() : ResponseResult.newFailResult();
    }

    @PostMapping("/updateFriend")
    public ResponseResult updateFriend(
            @RequestBody UpdateFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return friendshipService.updateFriend(req) ? ResponseResult.newSuccessResult() : ResponseResult.newFailResult();
    }

    @PostMapping("/deleteFriend")
    public ResponseResult deleteFriend(
            @RequestBody DeleteFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return friendshipService.deleteFriend(req) ? ResponseResult.newSuccessResult() : ResponseResult.newFailResult();
    }

    @PostMapping("/deleteAllFriend")
    public ResponseResult deleteAllFriend(
            @RequestBody DeleteFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        friendshipService.deleteAllFriend(req);
        return ResponseResult.newSuccessResult();
    }

    @PostMapping("/getAllFriendship")
    public ResponseResult getAllFriendship(
            @RequestBody GetFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return ResponseResult.newSuccessResult(friendshipService.getAllFriendship(req));
    }

    @PostMapping("/getFriendship")
    public ResponseResult getFriendship(
            @RequestBody GetFriendReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return ResponseResult.newSuccessResult(friendshipService.getFriendship(req));
    }


    @PostMapping("/checkFriendship")
    public ResponseResult checkFriendship(
            @RequestBody CheckFriendshipReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        return ResponseResult.newSuccessResult(friendshipService.checkFriendship(req));
    }
}
