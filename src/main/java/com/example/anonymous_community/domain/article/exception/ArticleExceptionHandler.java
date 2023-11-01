package com.example.anonymous_community.domain.article.exception;


import com.example.anonymous_community.global.common.ApiResponse;
import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.GlobalErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice("com.example.anonymous_community.domain.article")
public class ArticleExceptionHandler {

    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<ApiResponse<String>> articleBaseException(ArticleException e) {
        return new ResponseEntity<>(
                ApiResponse.fail(e.getStatusCode(), e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentTypeMismatch(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : null;
        log.info("Error Message: {}", errorMessage);
        ArticleException articleException;

        if (errorMessage != null) {
            if (ArticleValidationCode.ARTICLE_INDEX_PUT_ERROR.getMessage().equals(errorMessage)) {
                articleException = new ArticleException(ArticleErrorCode.ARTICLE_INDEX_PUT_ERROR);
            } else if (ArticleValidationCode.ARTICLE_ALL_COLUMNS_ERROR.getMessage().equals(errorMessage)) {
                articleException = new ArticleException(ArticleErrorCode.ARTICLE_ALL_COLUMNS_ERROR);
            } else if (ArticleValidationCode.ARTICLE_NEED_ONE_ERROR.getMessage().equals(errorMessage)) {
                articleException = new ArticleException(ArticleErrorCode.ARTICLE_NEED_ONE_ERROR);
            } else if (ArticleValidationCode.ARTICLE_EMPTY_ERROR.getMessage().equals(errorMessage)) {
                articleException = new ArticleException(ArticleErrorCode.ARTICLE_GET_ERROR);

            } else if (ArticleValidationCode.ARTICLE_PASSWORD_ERROR.getMessage().equals(errorMessage)) {
                articleException = new ArticleException(ArticleErrorCode.ARTICLE_PASSWORD_TYPE_ERROR);
            }
            else {
                articleException = new ArticleException(ArticleErrorCode.GLOBAL_ERROR_CODE);
            }
        }
        else {
            articleException = new ArticleException(ArticleErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(articleException.getStatusCode(), articleException.getMessage()),
                HttpStatus.valueOf(articleException.getStatusCode())
        );

    }

    //Integer여도 어떤 멤버변수냐에 따라 다르게 예외 처리
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        String missingParamName = e.getParameterName();
        ArticleException articleException;
        if ("articleIndex".equals(missingParamName)) {
            articleException = new ArticleException(ArticleErrorCode.ARTICLE_INDEX_NULL_ERROR);
        }

        else {
            articleException = new ArticleException(GlobalErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(articleException.getStatusCode(), articleException.getMessage()),
                HttpStatus.valueOf(articleException.getStatusCode())
        );
    }

    //현재는 list 조회할때 page 나 limit가 비어있는 값일때 처리해줌
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameter(NumberFormatException e, HttpServletRequest request) {
        ArticleException articleException;
        log.info("Error Message: {}", request.getMethod());
        log.info("Error Message: {}", request.getRequestURI());
        if (request.getMethod().equals("GET") && request.getRequestURI().equals(ArticleValidationCode.ARTICLE_GET_URI_ERROR.getMessage())) {
            articleException = new ArticleException(ArticleErrorCode.ARTICLE_GET_PAGE_LIMIT_ERROR);
        }


        else {
            articleException = new ArticleException(ArticleErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(articleException.getStatusCode(), articleException.getMessage()),
                HttpStatus.valueOf(articleException.getStatusCode())
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ArticleException articleException;
        log.info("Error Message: {}", request.getMethod());
        log.info("Error Message: {}", request.getRequestURI());


        if (request.getMethod().equals("PUT") && request.getRequestURI().equals(ArticleValidationCode.ARTICLE_PUT_URI_ERROR.getMessage())) {
            articleException = new ArticleException(ArticleErrorCode.ARTICLE_PUT_ARTICLEINDEX_ERROR);
        }

        else {
            articleException = new ArticleException(ArticleErrorCode.GLOBAL_ERROR_CODE);
        }


        return new ResponseEntity<>(
                ApiResponse.fail(articleException.getStatusCode(), articleException.getMessage()),
                HttpStatus.valueOf(articleException.getStatusCode())
        );
    }





}
