package com.example.anonymous_community.domain.comment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommentPutConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommentPutConstraint {
    String message() default "댓글 수정 작업이 실패하였습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
