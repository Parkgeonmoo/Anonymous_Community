package com.example.anonymous_community.domain.article.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ArticlePutConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticlePutConstraint {
    String message() default "글 수정 작업이 실패하였습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
