package com.example.anonymous_community.dto.request;

import com.example.anonymous_community.entity.ArticleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 파라미터 Dto
 *
 * @author parkgeonwoo
 */
@Data
@NoArgsConstructor
public class ArticleRequest {

    private String articleIndex;
    private String title;
    private String nickName;
    private String contents;
    private String password;

    public ArticleRequest(ArticleEntity articleEntity) {
        this.articleIndex = articleEntity.getArticleIndex();
        this.title = articleEntity.getTitle();
        this.nickName = articleEntity.getNickname();
        this.contents = articleEntity.getContents();
        this.password = articleEntity.getPassword();
    }

}
