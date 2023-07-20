package com.sugarpocket.im.service.friendship.web.dto;

import com.sugarpocket.im.common.model.BaseRequest;
import lombok.Data;

import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 23:50
 */
@Data
public class CheckFriendshipReq extends BaseRequest {
    private String sendId;
    private List<String> receiverIds;
    private Integer checkType;
}
