package com.sugarpocket.im.service.friendship.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sugarpocket.im.common.constant.CommonConstant;
import com.sugarpocket.im.common.enums.friendship.CheckFriendShipTypeEnum;
import com.sugarpocket.im.common.enums.friendship.FriendShipStatusEnum;
import com.sugarpocket.im.common.exception.ErrorCode;
import com.sugarpocket.im.common.exception.ServiceException;
import com.sugarpocket.im.service.friendship.dao.entity.TbFriendshipEntity;
import com.sugarpocket.im.service.friendship.dao.mapper.TbFriendshipMapper;
import com.sugarpocket.im.service.friendship.dao.resp.CheckFriendShipResp;
import com.sugarpocket.im.service.friendship.web.dto.*;
import com.sugarpocket.im.service.user.dao.entity.TbUserEntity;
import com.sugarpocket.im.service.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:38
 */
@Service
public class FriendshipService {
    @Autowired
    private TbFriendshipMapper friendshipMapper;
    @Autowired
    private UserService userService;

    public JSONObject importFriendship(ImportFriendshipReq req) {
        if (req.getFriendItem().size() > CommonConstant.IMPORT_MAX_SIZE) {
            throw new ServiceException(ErrorCode.IMPORT_SIZE_BEYOND);
        }

        JSONObject resp = new JSONObject();
        List<String> successId = new ArrayList<>();
        List<String> errorId = new ArrayList<>();

        req.getFriendItem().forEach(friendshipReq -> {
            TbFriendshipEntity friendship = new TbFriendshipEntity();
            BeanUtils.copyProperties(friendshipReq, friendship);
            friendship.setAppId(req.getAppId());
            friendship.setSendId(req.getSendId());
            try {
                if (friendshipMapper.insert(friendship) == 1) {
                    successId.add(friendship.getReceiverId());
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorId.add(friendship.getReceiverId());
            }


        });
        resp.put("size", successId.size());
        resp.put("sendId", req.getSendId());
        resp.put("successId", successId);
        resp.put("errorId", errorId);
        return resp;
    }

    public boolean addFriend(AddFriendReq req) {
        TbUserEntity fromUser = userService.getSingleUserInfo(req.getSendId(), req.getAppId());
        if (fromUser == null) {
            throw new ServiceException(ErrorCode.USER_NOT_EXIST);
        }
        TbUserEntity toUser = userService.getSingleUserInfo(req.getReceiverItem().getReceiverId(), req.getAppId());
        if (toUser == null) {
            throw new ServiceException(ErrorCode.USER_NOT_EXIST);
        }

        return doAddFriend(req.getSendId(), req.getReceiverItem(), req.getAppId());
    }

    public boolean updateFriend(UpdateFriendReq req) {
        return doUpdateFriend(req.getSendId(), req.getReceiverItem(), req.getAppId());
    }

    public boolean deleteFriend(DeleteFriendReq req) {
        return doDeleteFriend(req.getSendId(), req.getReceiverId(), req.getAppId());
    }

    public void deleteAllFriend(DeleteFriendReq req) {
        doDeleteAllFriend(req.getSendId(), req.getAppId());
    }

    public List<TbFriendshipEntity> getAllFriendship(GetFriendReq req) {
        return doGetAllFriendship(req.getSendId(), req.getAppId());
    }

    public TbFriendshipEntity getFriendship(GetFriendReq req) {
        return doGetFriendship(req.getSendId(), req.getReceiverId(), req.getAppId());
    }

    public List<CheckFriendShipResp> checkFriendship(CheckFriendshipReq req) {
        List<CheckFriendShipResp> res;
        Set<String> allIds = new HashSet<>(req.getReceiverIds());
        if (req.getCheckType() == CheckFriendShipTypeEnum.SINGLE.getType()) {
            res = friendshipMapper.checkFriendShipSingle(req);
        } else {
            res = friendshipMapper.checkFriendShipBoth(req);
        }
        Set<String> getIds = res.stream().map(CheckFriendShipResp::getReceiverId).collect(Collectors.toSet());
        for (String id : allIds) {
            if (!getIds.contains(id)) {
                CheckFriendShipResp checkFriendShipResp = new CheckFriendShipResp();
                checkFriendShipResp.setSendId(req.getSendId());
                checkFriendShipResp.setSendId(id);
                checkFriendShipResp.setStatus(0);
                res.add(checkFriendShipResp);
            }
        }
        return res;
    }

    public TbFriendshipEntity doGetFriendship(String sendId, String receiverId, Integer appId) {
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", sendId);
        wrapper.eq("receiver_id", receiverId);
        TbFriendshipEntity one = friendshipMapper.selectOne(wrapper);
        if (one == null) {
            throw new ServiceException(ErrorCode.FRIENDSHIP_FRIENDSHIP_IS_NOT_EXIST);
        }
        return one;
    }

    public List<TbFriendshipEntity> doGetAllFriendship(String sendId, Integer appId) {
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", sendId);
        return friendshipMapper.selectList(wrapper);
    }

    @Transactional
    public void doDeleteAllFriend(String sendId, Integer appId) {
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", sendId);
        wrapper.eq("status", FriendShipStatusEnum.FRIEND_STATUS_NORMAL.getCode());
        TbFriendshipEntity entity = new TbFriendshipEntity();
        entity.setStatus(FriendShipStatusEnum.FRIEND_STATUS_DELETE.getCode());
        friendshipMapper.update(entity, wrapper);
    }

    @Transactional
    public boolean doDeleteFriend(String sendId, String receiverId, Integer appId) {
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", sendId);
        wrapper.eq("receiver_id", receiverId);
        TbFriendshipEntity friendship = friendshipMapper.selectOne(wrapper);
        if (friendship != null) {
            if (friendship.getStatus().equals(FriendShipStatusEnum.FRIEND_STATUS_NORMAL.getCode())) {
                friendship.setStatus(FriendShipStatusEnum.FRIEND_STATUS_DELETE.getCode());
                return friendshipMapper.update(friendship, wrapper) == 1;
            }
        }
        throw new ServiceException(ErrorCode.FRIENDSHIP_USER_IS_NOT_YOUR_FRIEND);
    }

    @Transactional
    public boolean doAddFriend(String fromId, FriendDto dto, Integer appId) {
        //判断是否有记录存在 有则返回已经添加过 没有则添加
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", fromId);
        wrapper.eq("receiver_id", dto.getReceiverId());
        TbFriendshipEntity friendship = friendshipMapper.selectOne(wrapper);
        if (friendship == null) {
            //添加
            friendship = new TbFriendshipEntity();
            BeanUtils.copyProperties(dto, friendship);
            friendship.setAppId(appId);
            friendship.setSendId(fromId);
            friendship.setStatus(FriendShipStatusEnum.FRIEND_STATUS_NORMAL.getCode());
            friendship.setCreateTime(System.currentTimeMillis());
            return friendshipMapper.insert(friendship) == 1;
        } else {
            //存在记录
            //是好友
            if (friendship.getStatus().equals(FriendShipStatusEnum.FRIEND_STATUS_NORMAL.getCode())) {
                throw new ServiceException(ErrorCode.FRIENDSHIP_USER_IS_YOUR_FRIEND);
            } else {
                if (StringUtils.isNotBlank(dto.getAddSource())) {
                    friendship.setAddSource(dto.getAddSource());
                }

                if (StringUtils.isNotBlank(dto.getRemark())) {
                    friendship.setRemark(dto.getRemark());
                }

                if (StringUtils.isNotBlank(dto.getExtra())) {
                    friendship.setExtra(dto.getExtra());
                }
                friendship.setCreateTime(System.currentTimeMillis());
                return friendshipMapper.update(friendship, wrapper) == 1;
            }
        }
    }

    @Transactional
    public boolean doUpdateFriend(String fromId, FriendDto dto, Integer appId) {
        QueryWrapper<TbFriendshipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        wrapper.eq("send_id", fromId);
        wrapper.eq("receiver_id", dto.getReceiverId());
        TbFriendshipEntity friendship = friendshipMapper.selectOne(wrapper);
        if (friendship != null) {
            if (friendship.getStatus().equals(FriendShipStatusEnum.FRIEND_STATUS_NORMAL.getCode())) {
                if (StringUtils.isNotBlank(dto.getAddSource())) {
                    friendship.setAddSource(dto.getAddSource());
                }

                if (StringUtils.isNotBlank(dto.getRemark())) {
                    friendship.setRemark(dto.getRemark());
                }

                if (StringUtils.isNotBlank(dto.getExtra())) {
                    friendship.setExtra(dto.getExtra());
                }
                return friendshipMapper.update(friendship, wrapper) == 1;
            }
        }
        throw new ServiceException(ErrorCode.FRIENDSHIP_USER_IS_NOT_YOUR_FRIEND);
    }
}
