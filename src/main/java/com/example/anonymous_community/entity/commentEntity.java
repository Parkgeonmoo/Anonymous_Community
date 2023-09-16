package com.example.anonymous_community.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment_tb")
public class commentEntity {

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
    private String created_Time;

    @Column(name ="updated_Time")
    private String updated_Time;




}
