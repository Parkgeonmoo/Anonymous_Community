package com.example.anonymous_community.domain.comment.dto.request;

import com.example.anonymous_community.domain.comment.validation.CommentPasswordInputConstraint;
import com.example.anonymous_community.domain.comment.validation.CommentPutConstraint;
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
@CommentPutConstraint
public class CommentUpdateRequest {

    @NotNull(message ="글 인덱스를 입력해주세요")
    private Integer articleIndex;
    @NotNull(message ="댓글 인덱스를 입력해주세요")
    private Integer commentIndex;

    private String nickName;

    private String contents;
    @NotNull(message ="비밀번호를 입력해주세요")
    @NotEmpty(message ="비밀번호를 입력해주세요")
    @NotBlank(message ="비밀번호를 입력해주세요")
    @CommentPasswordInputConstraint
    private String password;
}
