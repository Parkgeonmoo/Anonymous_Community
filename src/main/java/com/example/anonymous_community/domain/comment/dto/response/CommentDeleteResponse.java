package com.example.anonymous_community.domain.comment.dto.response;

import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDeleteResponse {

    private Integer commentIndex;
    private Integer articleIndex;
    private String nickName;
    private String contents;
    private String password;

    public static CommentDeleteResponse fromEntity(CommentEntity commentEntity) {

        return CommentDeleteResponse.builder()
                .commentIndex(commentEntity.getCommentIndex())
                .articleIndex(commentEntity.getArticleIndex().getArticleIndex())
                .nickName(commentEntity.getNickName())
                .contents(commentEntity.getContents())
                .password(commentEntity.getPassword())
                .build();

    }
}
