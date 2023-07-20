package com.sugarpocket.im.service.friendship.dao.resp;

import lombok.Data;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/21 0:02
 */
@Data
public class CheckFriendShipResp {
    private String sendId;
    private String receiverId;
    private Integer status;
}
