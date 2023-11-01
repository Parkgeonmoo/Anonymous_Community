package com.example.anonymous_community.domain.article.dto.request;

import com.example.anonymous_community.domain.article.validation.ArticlePasswordInputConstraint;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 게시글 파라미터 Dto
 *
 * @author parkgeonwoo
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleEntryRequest {

    @NotNull(message="내용이 전부 입력되지 않았습니다.")
    @NotEmpty(message ="내용이 전부 입력되지 않았습니다.")
    @NotBlank(message ="내용이 전부 입력되지 않았습니다.")
    private String title;
    @NotNull(message="내용이 전부 입력되지 않았습니다.")
    @NotEmpty(message ="내용이 전부 입력되지 않았습니다.")
    @NotBlank(message ="내용이 전부 입력되지 않았습니다.")
    private String nickName;
    @NotNull(message="내용이 전부 입력되지 않았습니다.")
    @NotEmpty(message ="내용이 전부 입력되지 않았습니다.")
    @NotBlank(message ="내용이 전부 입력되지 않았습니다.")
    private String contents;
    @NotNull(message="내용이 전부 입력되지 않았습니다.")
    @NotEmpty(message ="내용이 전부 입력되지 않았습니다.")
    @NotBlank(message ="내용이 전부 입력되지 않았습니다.")
    @ArticlePasswordInputConstraint
    private String password;





}
