package com.example.anonymous_community.domain.comment.validation;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.global.common.GlobalValidationCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentPasswordInputConstraintValidator implements ConstraintValidator<CommentPasswordInputConstraint, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d).{6,10}$";

    @Override
    public void initialize(CommentPasswordInputConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!value.matches(PASSWORD_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(CommentErrorCode.COMMENT_PASSWORD_ERROR.getMessage())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
