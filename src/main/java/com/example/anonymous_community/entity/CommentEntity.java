package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.CommentRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "comment_tb")
public class CommentEntity {

    public CommentEntity(CommentRequest commentRequest) {
        this.commentIndex = commentRequest.getCommentIndex();
        this.articleIndex = commentRequest.getArticleIndex();
        this.nickName = commentRequest.getNickName();
        this.contents = commentRequest.getContents();
        this.password = commentRequest.getPassword();
        this.createdTime = LocalDateTime.now().toString();
        this.updatedTime = LocalDateTime.now().toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="comment_id", nullable = false)
    private String commentIndex;

    @Column(name ="article_id",nullable = false)
    private String articleIndex;

    @Column(name ="nickname",nullable = false)
    private String nickName;

    @Column(name ="contents")
    private String contents;

    @Column(name ="password",nullable = false)
    private String password;

    @Column(name ="created_time")
    private String createdTime;

    @Column(name ="updated_Time")
    private String updatedTime;




}
