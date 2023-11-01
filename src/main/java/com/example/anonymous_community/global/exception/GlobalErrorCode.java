package com.example.anonymous_community.global.exception;


import com.example.anonymous_community.global.exception.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum GlobalErrorCode implements ErrorCodeInterface{

    GLOBAL_ERROR_CODE(StatusCode.FAIL, 500, "서버에 문제가 생겨 잠시 후 시도해주세요.");

    private StatusCode statusCode;
    private int httpCode;
    private String message;


}
