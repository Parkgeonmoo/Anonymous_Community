package com.example.anonymous_community.domain.comment.validation;

import com.example.anonymous_community.domain.comment.dto.request.CommentUpdateRequest;
import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentPutConstraintValidator implements ConstraintValidator<CommentPutConstraint, CommentUpdateRequest> {

    @Override
    public void initialize(CommentPutConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CommentUpdateRequest commentUpdateRequest, ConstraintValidatorContext context) {
        if (commentUpdateRequest.getArticleIndex() == null || !(commentUpdateRequest.getArticleIndex() instanceof Integer)) {
            throw new BaseException(ErrorCode.COMMENT_WRONG_TYPE_ARTICLE_INDX_ERROR);
        }
        if (commentUpdateRequest.getArticleIndex() <= 0) {
            throw new BaseException(ErrorCode.COMMENT_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR);
        }

        if (commentUpdateRequest.getCommentIndex() == null || !(commentUpdateRequest.getArticleIndex() instanceof Integer)) {
            throw new BaseException(ErrorCode.COMMENT_WRONG_TYPE_COMMENT_INDX_ERROR);
        }
        if (commentUpdateRequest.getCommentIndex() <= 0) {
            throw new BaseException(ErrorCode.COMMENT_WRONG_BOUNDARY_COMMENT_INDEX_ERROR);
        }

        if (allColumns(commentUpdateRequest)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR.getMessage())
                    .addPropertyNode("contents")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    public boolean allColumns(CommentUpdateRequest commentUpdateRequest) {
        return StringUtils.isAllBlank (commentUpdateRequest.getContents(),
                commentUpdateRequest.getNickName());
    }


}
