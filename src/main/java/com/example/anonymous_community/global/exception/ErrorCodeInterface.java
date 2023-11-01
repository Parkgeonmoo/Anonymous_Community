package com.example.anonymous_community.global.exception;

public interface ErrorCodeInterface {

    StatusCode getStatusCode();
    int getHttpCode();
    String getMessage();

}
