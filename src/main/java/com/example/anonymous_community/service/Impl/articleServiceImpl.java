package com.example.anonymous_community.service.Impl;

import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.service.articleService;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.articledao;
import com.example.anonymous_community.dto.article;

import java.util.ArrayList;
import java.util.List;

@Service
public class articleServiceImpl implements articleService {
    articledao articledao;

    public articleServiceImpl(articledao articledao) {
        this.articledao = articledao;
    }

    public boolean postArticleService(String title,String nickname,String contents,String password) {
        articleEntity article = new articleEntity();
        article.setTitle(title);
        article.setNickname(nickname);
        article.setContents(contents);
        article.setPassword(password);

        boolean result = articledao.postArticleEntity(article);
        return result;
    }
    public article getArticleService(int articleIndex) {
        articleEntity article = articledao.getArticleEntity(articleIndex);
        article returnArticle = new article();
        returnArticle.setArticleIndex(article.getArticleIndex());
        returnArticle.setTitle(article.getTitle());
        returnArticle.setNickName(article.getNickname());
        returnArticle.setContents(article.getContents());
        returnArticle.setPassword(article.getPassword());

        return returnArticle;

    }

    public void putArticleService(int articleIndex,String title,String nickname,String contents,String password,String newpassword) {
        articleEntity article = new articleEntity();
        article.setTitle(title);
        article.setNickname(nickname);
        article.setContents(contents);
        article.setPassword(password);
        articledao.putArticleEntity(articleIndex,article,newpassword);
    }

    public void deleteArticleService(int articleIndex,String password){
        articledao.deleteArticleEntity(articleIndex,password);
    }



}
