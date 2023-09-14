package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.articleEntity;

public interface articledao {

    public boolean postArticleEntity(articleEntity article);
    public articleEntity getArticleEntity(int articleIndex);
    public void putArticleEntity(int articleIndex,articleEntity article,String newpassword);
    public void deleteArticleEntity(int articleIndex,String password);
}
