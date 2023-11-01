package com.example.anonymous_community.global.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{


    private ErrorCodeInterface errorCode;

    public BaseException() {

    }
    public BaseException(ErrorCodeInterface errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return this.errorCode.getHttpCode();
    }

}
