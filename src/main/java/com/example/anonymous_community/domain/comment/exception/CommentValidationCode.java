package com.example.anonymous_community.domain.comment.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentValidationCode {

    COMMENT_ARTICLE_INDEX_NEED_ERROR("글 인덱스를 입력해주세요"),
    COMMENT_NICKNAME_NEED_ERROR("닉네임을 입력해주세요"),
    COMMENT_CONTENTS_NEED_ERROR("내용을 입력해주세요"),
    COMMENT_PASSWORD_NEED_ERROR("비밀번호를 입력해주세요"),
    COMMENT_COMMENT_INDEX_NEED_ERROR("댓글 인덱스를 입력해주세요."),
    COMMENT_PASSWORD_ERROR("비밀번호 양식이 잘못되었습니다.영어 대소문자 한개와 숫자를 조합하여 6~10자리 내로 입력해주세요."),
    COMMENT_WRONG_TYPE_ARTICLE_INDX_ERROR("숫자 형태로 글 인덱스를 입력해주세요"),
    COMMENT_WRONG_TYPE_COMMENT_INDX_ERROR("숫자 형태로 댓글 인덱스를 입력해주세요"),
    COMMENT_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR("양수 이상의 숫자로만 글 인덱스를 입력해주세요"),
    COMMENT_WRONG_BOUNDARY_COMMENT_INDEX_ERROR("양수 이상의 숫자로만 댓글 인덱스를 입력해주세요"),
    COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR("닉네임과 내용 중 하나라도 수정할 내용이 필요합니다."),
    COMMENT_UNVALID_PASSWORD_ERROR("비밀번호가 틀립니다."),
    ;


    private String message;
}
