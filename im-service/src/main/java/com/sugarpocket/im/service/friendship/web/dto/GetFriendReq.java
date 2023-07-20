package com.sugarpocket.im.service.friendship.web.dto;

import com.sugarpocket.im.common.model.BaseRequest;
import lombok.Data;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 23:33
 */
@Data
public class GetFriendReq extends BaseRequest {
    private String sendId;
    private String receiverId;
}
