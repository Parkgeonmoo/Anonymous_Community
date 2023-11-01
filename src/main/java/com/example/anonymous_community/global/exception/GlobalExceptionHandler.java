package com.example.anonymous_community.global.exception;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.exception.ArticleValidationCode;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.domain.comment.exception.CommentValidationCode;
import com.example.anonymous_community.global.common.GlobalValidationCode;
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


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

}
