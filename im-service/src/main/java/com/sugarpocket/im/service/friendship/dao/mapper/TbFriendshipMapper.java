package com.sugarpocket.im.service.friendship.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sugarpocket.im.service.friendship.dao.entity.TbFriendshipEntity;
import com.sugarpocket.im.service.friendship.dao.resp.CheckFriendShipResp;
import com.sugarpocket.im.service.friendship.web.dto.CheckFriendshipReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:37
 */
@Mapper
public interface TbFriendshipMapper extends BaseMapper<TbFriendshipEntity> {


    List<CheckFriendShipResp> checkFriendShipSingle(CheckFriendshipReq req);

    List<CheckFriendShipResp> checkFriendShipBoth(CheckFriendshipReq req);
}
