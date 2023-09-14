package com.example.anonymous_community.controller;

import com.example.anonymous_community.dto.article;

import java.util.List;

public interface restapi {
    public Boolean postArticel(String title,String nickname,String contents,String password);
    public article getArticle(int articleIndex);
    public void putArticle(int articelsIndex,String title,String nickname,String contents,String password,String newpassword);
    public void deleteArticle(int articleIndex,String password);
}
