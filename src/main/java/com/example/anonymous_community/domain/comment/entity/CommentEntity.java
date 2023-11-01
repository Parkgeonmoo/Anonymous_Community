package com.example.anonymous_community.domain.comment.entity;

import com.example.anonymous_community.domain.article.dto.request.ArticleUpdateRequest;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.request.CommentUpdateRequest;
import com.example.anonymous_community.global.common.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 댓글 Entity
 *
 * @author parkgeonwoo
 */
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment_tb")
public class CommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="comment_id", nullable = false)
    private Integer commentIndex;

    @Column(name ="article_id",nullable = false)
    private Integer articleIndex;

    @Column(name ="nickname",nullable = false)
    private String nickName;

    @Column(name ="contents")
    private String contents;

    @Column(name ="password",nullable = false)
    private String password;



    private void setNickName(String nickName) {
        if (!StringUtils.isBlank(nickName)) {
            this.nickName = nickName;
        }
    }

    private void setContents(String contents) {
        if (!StringUtils.isBlank(contents)) {
            this.contents = contents;
        }
    }


    public void setAllColumns(CommentUpdateRequest inputCommentUpdateRequest) {
        setNickName(inputCommentUpdateRequest.getNickName());
        setContents(inputCommentUpdateRequest.getContents());
    }




}
