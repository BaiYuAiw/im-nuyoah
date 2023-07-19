package com.sugarpocket.im.service.user.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.sugarpocket.im.common.model.ResponseResult;
import com.sugarpocket.im.service.user.service.UserService;
import com.sugarpocket.im.service.user.web.dto.ImportUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 0:02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/importUser")
    public ResponseResult importUser(
            @RequestBody ImportUserReq req,
            @RequestParam("appId") Integer appId) {
        req.setAppId(appId);
        JSONObject obj = userService.importUser(req);
        return obj.getInteger("size") == req.getUserData().size() ?
                ResponseResult.newSuccessResult(obj) :
                ResponseResult.newFailResult(obj);
    }
}
