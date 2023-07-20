package com.sugarpocket.im.service.friendship.web.dto;

import lombok.Data;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 21:13
 */
@Data
public class FriendDto {
    private String receiverId;

    private String remark;

    private String addSource;

    private String extra;

    private String addWording;
}
