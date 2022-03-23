package org.example.common.core.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {


    private Integer code;
    private String message;


    public ServiceException() {
        this.code = 500;
        this.message = "系统错误";
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }


}
