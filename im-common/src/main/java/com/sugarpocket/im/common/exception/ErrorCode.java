package com.sugarpocket.im.common.exception;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 21:46
 */
public enum ErrorCode {
    //user 以10000开头
    IMPORT_SIZE_BEYOND(10000, "导入数量超出上限"),

    //通用
    FAILED(1, "操作失败"),
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
