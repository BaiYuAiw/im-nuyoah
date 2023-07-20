package com.sugarpocket.im.service.friendship.web.dto;

import com.sugarpocket.im.common.enums.friendship.FriendShipStatusEnum;
import com.sugarpocket.im.common.model.BaseRequest;
import lombok.Data;

import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:43
 */
@Data
public class ImportFriendshipReq extends BaseRequest {
    private String sendId;
    private List<ImportFriendDto> friendItem;

    @Data
    public static class ImportFriendDto{

        private String receiverId;

        private String remark;

        private String addSource;

        private Integer status = FriendShipStatusEnum.FRIEND_STATUS_NO_FRIEND.getCode();

        private Integer black = FriendShipStatusEnum.BLACK_STATUS_NORMAL.getCode();

    }
}
