package com.example.anonymous_community.domain.article.exception;

import com.example.anonymous_community.global.exception.ErrorCodeInterface;
import com.example.anonymous_community.global.exception.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements ErrorCodeInterface {
    ARTICLE_INDEX_PUT_ERROR(StatusCode.FAIL,400,"게시글 인덱스가 잘못되었습니다."),
    ARTICLE_INDEX_NULL_ERROR(StatusCode.FAIL,400,"인덱스를 입력해주세요."),
    ARTICLE_INDEX_DELETE_ERROR(StatusCode.FAIL,400,"지우고자 하는 글이 존재하지 않습니다."),
    ARTICLE_ALL_COLUMNS_ERROR(StatusCode.FAIL,400,"모든 게시글 내용이 입력되지 않았습니다."),
    ARTICLE_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR(StatusCode.FAIL,400,"양수 이상의 숫자로만 글 인덱스를 입력해주세요"),
    ARTICLE_PASSWORD_NEED_ERROR(StatusCode.FAIL,400,"비밀번호를 입력해주세요"),
    ARTICLE_WRONG_TYPE_ARTICLE_INDX_ERROR(StatusCode.FAIL,400,"숫자 형태로 글 인덱스를 입력해주세요"),
    ARTICLE_PASSWORD_NO_MATCH_ERROR(StatusCode.FAIL,400,"비밀번호가 일치하지 않습니다."),
    ARTICLE_NEED_ONE_ERROR(StatusCode.FAIL,400,"수정할 내용이 한개라도 필요합니다."),
    ARTICLE_POST_ERROR(StatusCode.FAIL, 400, "게시글 등록에 실패하였습니다."),
    ARTICLE_GET_PAGE_LIMIT_ERROR(StatusCode.FAIL,400,"올바른 페이지와 리밋을 입력해주세요"),
    ARTICLE_GET_ERROR(StatusCode.FAIL, 400, "게시글 조회에 실패하였습니다."),
    ARTICLE_PUT_FIND_ERROR(StatusCode.FAIL,400,"수정하고자 하는 글이 존재하지 않습니다."),
    ARTICLE_PASSWORD_TYPE_ERROR(StatusCode.FAIL,400,"비밀번호 양식이 잘못되었습니다.영어 대소문자 한개와 숫자를 조합하여 6~10자리 내로 입력해주세요."),
    ARTICLE_PAGE_ERROR(StatusCode.FAIL,400,"페이지 번호가 잘못되었습니다."),
    ARTICLE_LIMIT_ERROR(StatusCode.FAIL,400,"페이지 제한 범위가 잘못되었습니다."),
    ARTICLE_PAGE_WRONG_TYE_ERROR(StatusCode.FAIL,400,"페이지 범위를 숫자로 입력해주세요."),
    ARTICLE_LIMIT_WRONG_TYE_ERROR(StatusCode.FAIL,400,"페이지 제한 범위를 숫자로 입력해주세요."),
    ARTICLE_PUT_ERROR(StatusCode.FAIL, 400, "게시글 수정에 실패하였습니다."),
    ARTICLE_PUT_ARTICLEINDEX_ERROR(StatusCode.FAIL,400,"인덱스에는 숫자를 입력해주세요."),
    ARTICLE_DELETE_ERROR(StatusCode.FAIL, 400, "게시글 삭제에 실패하였습니다."),
    GLOBAL_ERROR_CODE(StatusCode.FAIL, 500, "서버에 문제가 생겨 잠시 후 시도해주세요.");
    ;

    private StatusCode statusCode;
    private int httpCode;
    private String message;

    }


