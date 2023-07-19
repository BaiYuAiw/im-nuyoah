package com.sugarpocket.im.common.exception;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 21:40
 */
public class ServiceException extends RuntimeException {

    /**
     * 用于保存错误码和错误消息的枚举对象，里面的值请根据需要自行指定。默认为ErrorCode.FAILED。
     */
    private final ErrorCode errorCode;

    public ServiceException(String msg) {
        super(msg);
        this.errorCode = ErrorCode.FAILED;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = ErrorCode.FAILED;
    }

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    /**
     * 获取ErrorCode，里面包含错误码和错误消息
     *
     * @return ErrorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
