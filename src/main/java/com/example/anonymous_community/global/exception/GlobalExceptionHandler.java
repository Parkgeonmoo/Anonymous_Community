package com.example.anonymous_community.global.exception;
import com.example.anonymous_community.global.common.ValidationCode;
import lombok.extern.slf4j.Slf4j;
import com.example.anonymous_community.global.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.example.anonymous_community.global.common.ValidationCode.*;
import static com.example.anonymous_community.global.exception.ErrorCode.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //실질적 예외 메시지 처리해주는 곳
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<String>> handleBaseException(BaseException e) {
        return new ResponseEntity<>(
                ApiResponse.fail(e.getStatusCode(), e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode())
        );
    }

    //@Valid를 통한 유효성 체크를 할 때 오류나는 곳 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentTypeMismatch(MethodArgumentNotValidException e) {

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : null;
        log.info("Error Message: {}", errorMessage);
        BaseException baseException;

        if (errorMessage != null) {
            if (ValidationCode.ARTICLE_INDEX_PUT_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.ARTICLE_INDEX_PUT_ERROR);
            }
            else if (ValidationCode.ARTICLE_ALL_COLUMNS_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.ARTICLE_ALL_COLUMNS_ERROR);
            }
            else if (ValidationCode.ARTICLE_NEED_ONE_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.ARTICLE_NEED_ONE_ERROR);
            }
            else if (ValidationCode.ARTICLE_EMPTY_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.ARTICLE_GET_ERROR);

            }
            else if (ValidationCode.ARTICLE_PASSWORD_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.ARTICLE_PASSWORD_TYPE_ERROR);
            }
            else if (ValidationCode.COMMENT_ARTICLE_INDEX_NEED_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_ARTICLE_INDEX_NEED_ERROR);
            }
            else if (ValidationCode.COMMENT_COMMENT_INDEX_NEED_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_COMMENT_INDEX_NEED_ERROR);
            }
            else if (ValidationCode.COMMENT_NICKNAME_NEED_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_NICKNAME_NEED_ERROR);
            }
            else if (ValidationCode.COMMENT_CONTENTS_NEED_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_CONTENTS_NEED_ERROR);
            }
            else if (ValidationCode.COMMENT_PASSWORD_NEED_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_PASSWORD_NEED_ERROR);
            }
            else if (ValidationCode.COMMENT_PASSWORD_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_PASSWORD_ERROR);
            }
            else if (ValidationCode.COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR);
            }
            else {
                baseException = new BaseException(ErrorCode.GLOBAL_ERROR_CODE);
            }

        } else {
            baseException = new BaseException(ErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(baseException.getStatusCode(), baseException.getMessage()),
                HttpStatus.valueOf(baseException.getStatusCode())
        );
    }

    //Integer여도 어떤 멤버변수냐에 따라 다르게 예외 처리
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        String missingParamName = e.getParameterName();
        BaseException baseException;
        if ("articleIndex".equals(missingParamName)) {
            baseException = new BaseException(ErrorCode.ARTICLE_INDEX_NULL_ERROR);
        }

        else {
            baseException = new BaseException(ErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(baseException.getStatusCode(), baseException.getMessage()),
                HttpStatus.valueOf(baseException.getStatusCode())
        );
    }
   
    //현재는 list 조회할때 page 나 limit가 비어있는 값일때 처리해줌
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameter(NumberFormatException e, HttpServletRequest request) {
        BaseException baseException;
        log.info("Error Message: {}", request.getMethod());
        log.info("Error Message: {}", request.getRequestURI());
        if (request.getMethod().equals("GET") && request.getRequestURI().equals(ValidationCode.ARTICLE_GET_URI_ERROR.getMessage())) {
            baseException = new BaseException(ErrorCode.ARTICLE_GET_PAGE_LIMIT_ERROR);
        }


        else {
            baseException = new BaseException(ErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(baseException.getStatusCode(), baseException.getMessage()),
                HttpStatus.valueOf(baseException.getStatusCode())
        );
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        BaseException baseException;
        log.info("Error Message: {}", request.getMethod());
        log.info("Error Message: {}", request.getRequestURI());

        if (request.getMethod().equals("PUT") && request.getRequestURI().equals(ValidationCode.ARTICLE_PUT_URI_ERROR.getMessage())) {
            baseException = new BaseException(ErrorCode.ARTICLE_PUT_ARTICLEINDEX_ERROR);
        }

        else {
            baseException = new BaseException(ErrorCode.GLOBAL_ERROR_CODE);
        }


        return new ResponseEntity<>(
                ApiResponse.fail(baseException.getStatusCode(), baseException.getMessage()),
                HttpStatus.valueOf(baseException.getStatusCode())
        );
        }






}
