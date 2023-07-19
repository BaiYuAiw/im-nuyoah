package com.sugarpocket.im.service.user.web;

import com.sugarpocket.im.common.exception.ErrorCode;
import com.sugarpocket.im.common.exception.ServiceException;
import com.sugarpocket.im.common.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 23:03
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义报错异常，若异常中的httpStatus为4xx或5xx错误，则返回的状态码也是4xx或5xx。
     *
     * @see ServiceException
     */
    @ExceptionHandler(value = ServiceException.class)
    public Object handleHelloServiceException(HttpServletResponse response, ServiceException e) throws IOException {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseResult.newFailResult(errorCode.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = {TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleBadRequest(Exception exception, WebRequest request) {
        return ResponseResult.newFailResult(400, "Bad Request");
    }

    /**
     * 处理其他异常。这里返回的HTTP状态码和错误码都是500。
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleException(Exception exception, WebRequest request) {
        log.error("@@ServerException Exception={} Request={}", exception, request.getDescription(true));
        log.error("@@StackTrace", exception);
        return ResponseResult.newFailResult(500, "服务繁忙，请重试");
    }
}
