package com.example.anonymous_community.global.exception;

import lombok.Getter;

@Getter
public class BaseException extends  RuntimeException{

    private ErrorCode errorCode;
    private ErrorCodeInterface errorCodes;

    public BaseException() {

    }
    //도메인별 예외 처리 
    public BaseException(ErrorCodeInterface errorCodes) {
        super(errorCodes.getMessage());
        this.errorCodes = errorCodes;
    }
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return this.errorCode.getHttpCode();
    }

}
