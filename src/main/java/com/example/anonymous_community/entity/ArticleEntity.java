package com.example.anonymous_community.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Data
@Entity
@Table(name = "article_tb")
public class ArticleEntity {

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

    @Column
    private String created_time;

    @Column
    private String updated_time;


}
