package com.example.anonymous_community.dto;

import lombok.Data;

@Data
public class Comment {

    private String commentIndex;
    private String articleIndex;
    private String nickName;
    private String contents;
    private String password;
    private String createdTime;
    private String updatedTime;

}