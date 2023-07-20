package com.sugarpocket.im.common.enums.friendship;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:51
 */
public enum FriendShipStatusEnum {
    //通用
    SUCCESS(1),
    FAIL(0),
    /**
     * 0未添加 1正常 2删除
     */
    FRIEND_STATUS_NO_FRIEND(0),

    FRIEND_STATUS_NORMAL(1),

    FRIEND_STATUS_DELETE(2),

    /**
     * 0未添加 1正常 2拉黑
     */
    BLACK_STATUS_NORMAL(1),

    BLACK_STATUS_BLACKED(2),

    //add_friend
    IS_YOUR_FRIEND(-1); //已经是你的好友
    ;

    private Integer code;

    FriendShipStatusEnum(int code){
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}
