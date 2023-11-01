package com.example.anonymous_community.domain.article.validation;

import com.example.anonymous_community.domain.article.dto.request.ArticleUpdateRequest;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.global.common.GlobalValidationCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;




public class ArticlePutConstraintValidator implements ConstraintValidator<ArticlePutConstraint, ArticleUpdateRequest> {

    @Override
    public void initialize(ArticlePutConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ArticleUpdateRequest articleUpdateRequest, ConstraintValidatorContext context) {
        if (articleUpdateRequest.getArticleIndex() != null && articleUpdateRequest.getArticleIndex() < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ArticleErrorCode.ARTICLE_INDEX_PUT_ERROR.getMessage())
                    .addPropertyNode("articleIndex")
                    .addConstraintViolation();
            return false;
        }

        if (allColumns(articleUpdateRequest)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ArticleErrorCode.ARTICLE_NEED_ONE_ERROR.getMessage())
                    .addPropertyNode("title")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
    public boolean allColumns(ArticleUpdateRequest articleUpdateRequest) {
        return StringUtils.isAllBlank (articleUpdateRequest.getTitle(),
                articleUpdateRequest.getNickName(),articleUpdateRequest.getContents());
    }
}
