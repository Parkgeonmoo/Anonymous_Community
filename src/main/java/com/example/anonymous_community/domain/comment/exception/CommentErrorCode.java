package com.example.anonymous_community.domain.comment.exception;

import com.example.anonymous_community.global.exception.ErrorCodeInterface;
import com.example.anonymous_community.global.exception.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentErrorCode implements ErrorCodeInterface {

    COMMENT_ARTICLE_INDEX_NEED_ERROR(StatusCode.FAIL,400,"글 인덱스를 입력해주세요"),
    COMMENT_COMMENT_INDEX_NEED_ERROR(StatusCode.FAIL,400,"댓글 인덱스를 입력해주세요"),
    COMMENT_WRONG_TYPE_ARTICLE_INDX_ERROR(StatusCode.FAIL,400,"숫자 형태로 글 인덱스를 입력해주세요"),
    COMMENT_WRONG_TYPE_COMMENT_INDX_ERROR(StatusCode.FAIL,400,"숫자 형태로 댓글 인덱스를 입력해주세요"),
    COMMENT_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR(StatusCode.FAIL,400,"양수 이상의 숫자로만 글 인덱스를 입력해주세요"),
    COMMENT_WRONG_BOUNDARY_COMMENT_INDEX_ERROR(StatusCode.FAIL,400,"양수 이상의 숫자로만 댓글 인덱스를 입력해주세요"),
    COMMENT_NICKNAME_NEED_ERROR(StatusCode.FAIL,400,"닉네임을 입력해주세요"),
    COMMENT_CONTENTS_NEED_ERROR(StatusCode.FAIL,400,"내용을 입력해주세요"),
    COMMENT_PASSWORD_NEED_ERROR(StatusCode.FAIL,400,"비밀번호를 입력해주세요"),
    COMMENT_ENTRY_ARTICLE_INDEX_FAIL_ERROR(StatusCode.FAIL,400,"댓글을 달고자 하는 글이 존재하지 않습니다."),
    COMMENT_PUT_ARTICLE_INDEX_FAIL_ERROR(StatusCode.FAIL,400,"댓글을 수정하려는 글이 존재하지 않습니다."),
    COMMENT_PUT_COMMENT_INDEX_FAIL_ERROR(StatusCode.FAIL,400,"수정하고자 하는 댓글이 존재하지 않습니다."),
    COMMENT_PASSWORD_ERROR(StatusCode.FAIL,400,"비밀번호 양식이 잘못되었습니다.영어 대소문자 한개와 숫자를 조합하여 6~10자리 내로 입력해주세요."),
    COMMENT_UNVALID_PASSWORD_ERROR(StatusCode.FAIL,400,"비밀번호가 틀립니다."),
    COMMENT_POST_ERROR(StatusCode.FAIL, 400, "댓글 등록에 실패하였습니다."),
    COMMENT_GET_ERROR(StatusCode.FAIL, 400, "댓글 조회에 실패하였습니다."),
    COMMENT_PUT_ERROR(StatusCode.FAIL, 400, "댓글 수정에 실패하였습니다."),
    COMMENT_DELETE_ERROR(StatusCode.FAIL, 400, "댓글 삭제에 실패하였습니다"),
    COMMENT_CONTENTS_NICKNAME_NEED_ONE_ERROR(StatusCode.FAIL,400,"닉네임과 내용 중 하나라도 수정할 내용이 필요합니다."),
    COMMENT_GET_LIST_ERROR(StatusCode.FAIL,400,"댓글을 조회해오려는 글이 존재하지 않습니다."),
    GLOBAL_ERROR_CODE(StatusCode.FAIL, 500, "서버에 문제가 생겨 잠시 후 시도해주세요."),
    ;

    private StatusCode statusCode;
    private int httpCode;
    private String message;

    }
