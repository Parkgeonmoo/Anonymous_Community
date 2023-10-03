package com.example.anonymous_community.dto;

import com.example.anonymous_community.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class CommentRequest {

    public CommentRequest(CommentEntity commentEntity) {
        this.commentIndex = commentEntity.getArticleIndex();
        this.articleIndex = commentEntity.getArticleIndex();
        this.nickName = commentEntity.getNickName();
        this.contents = commentEntity.getContents();
        this.password = commentEntity.getPassword();

    }

    private String commentIndex;
    private String articleIndex;
    private String nickName;
    private String contents;
    private String password;

}