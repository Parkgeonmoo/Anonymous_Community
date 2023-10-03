package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "article_tb")
public class ArticleEntity {

    public ArticleEntity(Article inputArticle) {
        this.articleIndex = inputArticle.getArticleIndex();
        this.title = inputArticle.getTitle();
        this.nickname = inputArticle.getNickName();
        this.contents = inputArticle.getContents();
        this.password = inputArticle.getPassword();
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
