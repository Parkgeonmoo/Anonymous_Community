package com.example.anonymous_community.global.exception;

import com.example.anonymous_community.global.exception.enums.StatusCode;

public interface ErrorCodeInterface {

    StatusCode getStatusCode();
    int getHttpCode();
    String getMessage();

}
