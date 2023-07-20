package com.sugarpocket.im.common.exception;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 21:46
 */
public enum ErrorCode {
    //user 以10000开头
    USER_NOT_EXIST(10000,"用户不存在"),

    //friendship 以20000开头
    FRIENDSHIP_USER_IS_YOUR_FRIEND(20000,"用户已经是你的好友,请勿重复添加"),
    FRIENDSHIP_USER_IS_NOT_YOUR_FRIEND(20001,"该用户不是你的好友,删除失败"),
    FRIENDSHIP_FRIENDSHIP_IS_NOT_EXIST(20002,"关系链不存在"),


    //通用
    FAILED(1, "操作失败"),
    IMPORT_SIZE_BEYOND(10000, "导入数量超出上限"),
    PARAM_ERROR(400, "参数错误"),
    ;
    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误消息
     */
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
