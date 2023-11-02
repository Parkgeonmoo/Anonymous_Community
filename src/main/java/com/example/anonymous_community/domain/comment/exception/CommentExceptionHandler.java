package com.example.anonymous_community.domain.comment.exception;


import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.global.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.example.anonymous_community.domain.comment")
public class CommentExceptionHandler {

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ApiResponse<String>> commentBaseException(CommentException e) {
        return new ResponseEntity<>(
                ApiResponse.fail(e.getStatusCode(), e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode())
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<String>> commentMissingException(MissingServletRequestParameterException e) {
        String missingParamName = e.getParameterName();
        CommentException commentException;

        if ("articleIndex".equals(missingParamName)) {
            commentException = new CommentException(CommentErrorCode.COMMENT_ARTICLE_INDEX_NEED_ERROR);
        }

        else {
            commentException = new CommentException(CommentErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(commentException.getStatusCode(), commentException.getMessage()),
                HttpStatus.valueOf(commentException.getStatusCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentTypeMismatch(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : null;
        log.info("Error Message: {}", errorMessage);
        CommentException commentException;


        if (errorMessage != null) {
            if (CommentValidationCode.COMMENT_ARTICLE_INDEX_NEED_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_ARTICLE_INDEX_NEED_ERROR);
            }
            else if (CommentValidationCode.COMMENT_COMMENT_INDEX_NEED_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_COMMENT_INDEX_NEED_ERROR);
            }
            else if (CommentValidationCode.COMMENT_NICKNAME_NEED_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_NICKNAME_NEED_ERROR);
            }
            else if (CommentValidationCode.COMMENT_CONTENTS_NEED_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_CONTENTS_NEED_ERROR);
            }
            else if (CommentValidationCode.COMMENT_PASSWORD_NEED_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_PASSWORD_NEED_ERROR);
            }
            else if (CommentValidationCode.COMMENT_PASSWORD_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_PASSWORD_ERROR);
            }
            else if (CommentValidationCode.COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR.getMessage().equals(errorMessage)) {
                commentException = new CommentException(CommentErrorCode.COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR);
            }
            else {
                commentException = new CommentException(CommentErrorCode.GLOBAL_ERROR_CODE);
            }

        } else {
            commentException = new CommentException(CommentErrorCode.GLOBAL_ERROR_CODE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(commentException.getStatusCode(), commentException.getMessage()),
                HttpStatus.valueOf(commentException.getStatusCode())
        );

    }


}
