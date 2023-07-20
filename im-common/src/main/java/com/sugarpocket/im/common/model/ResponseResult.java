package com.sugarpocket.im.common.model;

import com.sugarpocket.im.common.exception.ErrorCode;
import lombok.Data;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 21:51
 */
@Data
public class ResponseResult<D> {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;


    protected D data;

    protected int code;

    protected String msg;

    public static final String DEFAULT_SUCCESS_MESSAGE = "OK";

    public static final String DEFAULT_FAIL_MESSAGE = "Failed";

    public static <D> ResponseResult<D> newSuccessResult(D data) {
        ResponseResult<D> responseResult = new ResponseResult<>();
        responseResult.setData(data);
        responseResult.setCode(SUCCESS);
        responseResult.setMsg(DEFAULT_SUCCESS_MESSAGE);
        return responseResult;
    }

    public static ResponseResult<?> newSuccessResult() {
        ResponseResult<?> responseResult = new ResponseResult<>();
        responseResult.setCode(SUCCESS);
        responseResult.setMsg(DEFAULT_SUCCESS_MESSAGE);

        return responseResult;
    }

    public static ResponseResult<?> newSuccessResult(String msg) {
        ResponseResult<?> responseResult = new ResponseResult<>();
        responseResult.setCode(SUCCESS);
        responseResult.setMsg(msg);

        return responseResult;
    }


    public static ResponseResult<?> newFailResult() {
        ResponseResult<?> responseResult = new ResponseResult<>();
        responseResult.setCode(FAILURE);
        responseResult.setMsg(DEFAULT_FAIL_MESSAGE);
        return responseResult;
    }

    public static <D> ResponseResult<D> newFailResult(D data) {
        ResponseResult<D> responseResult = new ResponseResult<>();
        responseResult.setData(data);
        responseResult.setCode(FAILURE);
        responseResult.setMsg(DEFAULT_FAIL_MESSAGE);
        return responseResult;
    }

    public static ResponseResult<?> newFailResult(int code, String msg) {
        ResponseResult<?> responseResult = new ResponseResult<>();
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        return responseResult;
    }

    public static <D> ResponseResult<D> newFailResultWithErrorData(D errorData) {
        ResponseResult<D> responseResult = new ResponseResult<>();
        responseResult.setData(errorData);
        responseResult.setCode(FAILURE);
        responseResult.setMsg(DEFAULT_FAIL_MESSAGE);
        return responseResult;
    }

    public static ResponseResult<?> newFailResult(ErrorCode code) {
        ResponseResult<?> responseResult = new ResponseResult<>();
        responseResult.setCode(code.getCode());
        responseResult.setMsg(code.getMsg());
        return responseResult;
    }


}
