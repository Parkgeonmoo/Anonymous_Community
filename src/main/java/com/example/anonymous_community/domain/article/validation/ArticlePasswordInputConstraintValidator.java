package com.example.anonymous_community.domain.article.validation;

import com.example.anonymous_community.global.common.ValidationCode;
import com.example.anonymous_community.global.exception.ErrorCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.example.anonymous_community.global.common.ValidationCode.ARTICLE_PASSWORD_ERROR;

public class ArticlePasswordInputConstraintValidator implements ConstraintValidator<ArticlePasswordInputConstraint, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d).{6,10}$";

    @Override
    public void initialize(ArticlePasswordInputConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!value.matches(PASSWORD_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ValidationCode.ARTICLE_PASSWORD_ERROR.getMessage())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
