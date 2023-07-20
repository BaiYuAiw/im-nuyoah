package com.sugarpocket.im.service.user.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugarpocket.im.common.constant.CommonConstant;
import com.sugarpocket.im.common.exception.ErrorCode;
import com.sugarpocket.im.common.exception.ServiceException;
import com.sugarpocket.im.service.user.dao.entity.TbUserEntity;
import com.sugarpocket.im.service.user.dao.mapper.TbUserMapper;
import com.sugarpocket.im.service.user.web.dto.ImportUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 22:30
 */
@Service
public class UserService {
    @Autowired
    private TbUserMapper userMapper;

    public JSONObject importUser(ImportUserReq req) {
        if (req.getUserData().size() > CommonConstant.IMPORT_MAX_SIZE) {
            throw new ServiceException(ErrorCode.IMPORT_SIZE_BEYOND);
        }
        JSONObject resp = new JSONObject();
        List<String> successId = new ArrayList<>();
        List<String> errorId = new ArrayList<>();

        req.getUserData().forEach(user -> {
            user.setAppId(req.getAppId());
            try {
                if (userMapper.insert(user) == 1) {
                    successId.add(user.getUserId());
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorId.add(user.getUserId());
            }
        });
        resp.put("size", successId.size());
        resp.put("successId", successId);
        resp.put("errorId", errorId);
        return resp;
    }

    public TbUserEntity getSingleUserInfo(String userId, Integer appId) {
        QueryWrapper<TbUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("user_id", userId);
        wrapper.eq("del_flag", 0);
        return userMapper.selectOne(wrapper);
    }
}
