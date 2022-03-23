package org.example.common.global.handler;

import org.example.common.core.constants.ResultData;
import org.example.common.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultData<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                  HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return ResultData.fail(e.getMessage());
    }


    /**
     * 校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultData<Object> resolveConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ResultData.fail(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResultData.fail(message);
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultData<Object> handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResultData.fail(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultData<Object> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return ResultData.fail(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData<Object> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return ResultData.fail(e.getMessage());
    }

}
