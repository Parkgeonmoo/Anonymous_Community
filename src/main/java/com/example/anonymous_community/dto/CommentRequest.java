package com.example.anonymous_community.dto;

import com.example.anonymous_community.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 댓글 파라미터 Dto
 *
 * @author parkgeonwoo
 */
@Data
@NoArgsConstructor(force = true)
public class CommentRequest {

    private String commentIndex;
    private String articleIndex;
    private String nickName;
    private String contents;
    private String password;

    public CommentRequest(CommentEntity commentEntity) {
        this.commentIndex = commentEntity.getArticleIndex();
        this.articleIndex = commentEntity.getArticleIndex();
        this.nickName = commentEntity.getNickName();
        this.contents = commentEntity.getContents();
        this.password = commentEntity.getPassword();

    }

}