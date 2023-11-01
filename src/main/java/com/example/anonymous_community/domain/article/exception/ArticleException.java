package com.example.anonymous_community.domain.article.exception;

import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.ErrorCodeInterface;
import lombok.Getter;


@Getter
public class ArticleException extends BaseException {

    private ErrorCodeInterface errorCode;

    public ArticleException() {

    }
    public ArticleException(ErrorCodeInterface errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public int getStatusCode() {

        return this.errorCode.getHttpCode();
    }



}
