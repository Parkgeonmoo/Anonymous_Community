package com.example.anonymous_community.dto;

import lombok.Data;


@Data
public class Article {
    String articleIndex;
    String title;
    String nickName;
    String contents;
    String password;
    String created_Time;
    String updated_Time;

    public Article() {

    }


}
