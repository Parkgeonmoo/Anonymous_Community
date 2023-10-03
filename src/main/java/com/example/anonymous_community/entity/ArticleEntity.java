package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.ArticleRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "article_tb")
public class ArticleEntity {

    public ArticleEntity(ArticleRequest inputArticleRequest) {
        this.articleIndex = inputArticleRequest.getArticleIndex();
        this.title = inputArticleRequest.getTitle();
        this.nickname = inputArticleRequest.getNickName();
        this.contents = inputArticleRequest.getContents();
        this.password = inputArticleRequest.getPassword();
        this.createdTime = LocalDateTime.now().toString();
        this.updatedTime = LocalDateTime.now().toString();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = LocalDateTime.now().toString();
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="article_id", nullable = false)
    private String articleIndex;

    @Column
    private String title;

    @Column
    private String nickname;

    @Column
    private String contents;

    @Column
    private String password;

    @Column(name="created_time")
    private String createdTime;

    @Column(name="updated_time")
    private String updatedTime;




}
