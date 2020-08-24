package com.pyruz.samotech.shared.handler.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;
    private HttpStatus httpStatus;


    public ServiceException() {
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}

