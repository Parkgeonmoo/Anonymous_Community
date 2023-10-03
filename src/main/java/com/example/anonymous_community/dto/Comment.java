package com.example.anonymous_community.dto;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.Data;

@Data
public class Comment {

    public Comment() {

    }

    public Comment(CommentEntity commentEntity) {
        this.commentIndex = commentEntity.getArticleIndex();
        this.articleIndex = commentEntity.getArticleIndex();
        this.nickName = commentEntity.getNickName();
        this.contents = commentEntity.getContents();
        this.password = commentEntity.getPassword();
        this.createdTime = commentEntity.getCreatedTime();
        this.updatedTime = commentEntity.getUpdatedTime();
    }

    private String commentIndex;
    private String articleIndex;
    private String nickName;
    private String contents;
    private String password;
    private String createdTime;
    private String updatedTime;

}