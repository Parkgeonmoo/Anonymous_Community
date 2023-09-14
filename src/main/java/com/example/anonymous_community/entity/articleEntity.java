package com.example.anonymous_community.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "article_tb")
public class articleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="article_id", nullable = false)
    private int articleIndex;

    @Column
    private String title;

    @Column
    private String nickname;

    @Column
    private String contents;

    @Column
    private String password;


}
