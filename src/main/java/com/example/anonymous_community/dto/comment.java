package com.example.anonymous_community.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class comment {

    private String commentIndex;
    private String articleIndex;
    private String nickName;
    private String contents;
    private String password;
    private String created_Time;
    private String updated_Time;

}