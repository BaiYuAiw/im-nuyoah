package com.sugarpocket.im.common.enums.friendship;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 23:53
 */
public enum CheckFriendShipTypeEnum {
    /**
     * 1 单方校验；2双方校验。
     */
    SINGLE(1),

    BOTH(2),
    ;

    private int type;

    CheckFriendShipTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
