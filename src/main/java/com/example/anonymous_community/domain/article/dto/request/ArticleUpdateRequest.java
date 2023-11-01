package com.example.anonymous_community.domain.article.dto.request;

import com.example.anonymous_community.domain.article.validation.ArticlePutConstraint;
import com.example.anonymous_community.domain.article.validation.ArticlePasswordInputConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ArticlePutConstraint
public class ArticleUpdateRequest {

    @NotNull(message="잘못된 인덱스입니다.")
    private Integer articleIndex;

    private String title;

    private String nickName;

    private String contents;
    @NotNull(message="내용이 전부 입력되지 않았습니다.")
    @NotEmpty(message ="내용이 전부 입력되지 않았습니다.")
    @NotBlank(message ="내용이 전부 입력되지 않았습니다.")
    @ArticlePasswordInputConstraint
    private String password;
}
