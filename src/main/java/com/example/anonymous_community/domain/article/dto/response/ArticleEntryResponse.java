package com.example.anonymous_community.domain.article.dto.response;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
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
public class ArticleEntryResponse {


    private Integer articleIndex;
    private String title;
    private String nickName;
    private String contents;
    private String password;

    public static ArticleEntryResponse fromEntity(ArticleEntity articleEntity) {
        return ArticleEntryResponse.builder()
                .articleIndex(articleEntity.getArticleIndex())
                .title(articleEntity.getTitle())
                .nickName(articleEntity.getNickname())
                .contents(articleEntity.getContents())
                .password(articleEntity.getPassword())
                .build();
    }
}
