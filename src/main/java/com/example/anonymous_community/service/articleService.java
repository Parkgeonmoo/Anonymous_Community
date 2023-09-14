package com.example.anonymous_community.service;

import com.example.anonymous_community.dto.article;

public interface articleService {
    public boolean postArticleService(String title,String nickname,String contents,String password);
    public article getArticleService(int articleIndex);
    public void putArticleService(int articleIndex,String title,String nickname,String contents,String password,String newpassword);
    public void deleteArticleService(int articleIndex,String password);
}
