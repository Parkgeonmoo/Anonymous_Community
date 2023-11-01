package com.example.anonymous_community.domain.article.dto.response;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDeleteResponse {

    private Integer articleIndex;
    private String title;
    private String nickName;
    private String contents;
    private String password;

    public static ArticleDeleteResponse fromEntity(ArticleEntity articleEntity) {
       return  ArticleDeleteResponse.builder()
                .articleIndex(articleEntity.getArticleIndex())
                .title(articleEntity.getTitle())
                .nickName(articleEntity.getNickname())
                .contents(articleEntity.getContents())
                .password(articleEntity.getPassword())
                .build();
    }
}
