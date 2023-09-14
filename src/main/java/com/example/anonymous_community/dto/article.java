package com.example.anonymous_community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class article {
    int articleIndex;
    String title;
    String nickName;
    String contents;
    String password;
    String newpassword;

    public article() {

    }
    public article(int articleIndex,String title,String nickName,String contents,String password,String newpassword) {
        this.articleIndex = articleIndex;
        this.title = title;
        this.nickName = nickName;
        this.contents = contents;
        this.password = password;
        this.newpassword = newpassword;
    }

}
