package com.example.anonymous_community.dto;

import com.example.anonymous_community.entity.ArticleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ArticleRequest {

    public ArticleRequest(ArticleEntity articleEntity) {
        this.articleIndex = articleEntity.getArticleIndex();
        this.title = articleEntity.getTitle();
        this.nickName = articleEntity.getNickname();
        this.contents = articleEntity.getContents();
        this.password = articleEntity.getPassword();
    }

    private String articleIndex;
    private String title;
    private String nickName;
    private String contents;
    private String password;


}
