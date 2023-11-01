package com.example.anonymous_community.domain.article.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleValidationCode {

    ARTICLE_INDEX_PUT_ERROR("잘못된 인덱스입니다."),
    ARTICLE_ALL_COLUMNS_ERROR("내용이 전부 입력되지 않았습니다."),
    ARTICLE_NEED_ONE_ERROR("수정할 내용이 한개라도 필요합니다."),
    ARTICLE_EMPTY_ERROR("인덱스가 비었습니다."),
    ARTICLE_PASSWORD_ERROR("비밀번호 양식이 잘못되었습니다.영어 대소문자 한개와 숫자를 조합하여 6~10자리 내로 입력해주세요."),
    ARTICLE_GET_URI_ERROR("/api/article/articles"),
    ARTICLE_PUT_URI_ERROR("/api/article/article"),
    ;


    private String message;
}
