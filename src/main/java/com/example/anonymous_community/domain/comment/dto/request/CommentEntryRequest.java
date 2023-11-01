package com.example.anonymous_community.domain.comment.dto.request;

import com.example.anonymous_community.domain.article.validation.ArticlePutConstraint;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.domain.comment.validation.CommentPasswordInputConstraint;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 댓글 파라미터 Dto
 *
 * @author parkgeonwoo
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentEntryRequest {

    @NotNull(message ="글 인덱스를 입력해주세요")
    private Integer articleIndex;
    @NotNull(message ="닉네임을 입력해주세요")
    @NotEmpty(message ="닉네임을 입력해주세요")
    @NotBlank(message ="닉네임을 입력해주세요")
    private String nickName;
    @NotNull(message ="내용을 입력해주세요")
    @NotEmpty(message ="내용을 입력해주세요")
    @NotBlank(message ="내용을 입력해주세요")
    private String contents;
    @NotNull(message ="비밀번호를 입력해주세요")
    @NotEmpty(message ="비밀번호를 입력해주세요")
    @NotBlank(message ="비밀번호를 입력해주세요")
    @CommentPasswordInputConstraint
    private String password;



}