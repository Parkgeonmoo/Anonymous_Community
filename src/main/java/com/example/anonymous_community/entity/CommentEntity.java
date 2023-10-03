package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.Comment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment_tb")
public class CommentEntity {

    public CommentEntity() {

    }

    public CommentEntity(Comment comment) {
        this.commentIndex = comment.getCommentIndex();
        this.articleIndex = comment.getArticleIndex();
        this.nickName = comment.getNickName();
        this.contents = comment.getContents();
        this.password = comment.getPassword();
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
