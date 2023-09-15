package com.example.anonymous_community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class article {
    int articleIndex;
    String title;
    String nickName;
    String contents;
    String password;
    String created_Time;
    String updated_Time;

    public article() {

    }


}
