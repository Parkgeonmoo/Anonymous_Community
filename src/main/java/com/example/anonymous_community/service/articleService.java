package com.example.anonymous_community.service;

import com.example.anonymous_community.entity.articleEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dto.article;

@Service
public class articleService {
    com.example.anonymous_community.dao.articledao articledao;

    public articleService(com.example.anonymous_community.dao.articledao articledao) {
        this.articledao = articledao;
    }

    public article postArticleService(article inputArticle) {
        articleEntity articleEntity = new articleEntity();
        articleEntity returnArticleEntity = new articleEntity();
        article returnArticle = new article();
        try {
            if (inputArticle != null) {
                articleEntity.setTitle(inputArticle.getTitle());
                articleEntity.setNickname(inputArticle.getNickName());
                articleEntity.setContents(inputArticle.getContents());
                articleEntity.setPassword(inputArticle.getPassword());
                returnArticleEntity = articledao.postArticleEntity(articleEntity);

                if (returnArticleEntity != null) {
                    returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
                    returnArticle.setTitle(returnArticleEntity.getTitle());
                    returnArticle.setNickName(returnArticleEntity.getNickname());
                    returnArticle.setContents(returnArticleEntity.getContents());
                    returnArticle.setPassword(returnArticleEntity.getPassword());
                    returnArticle.setCreated_Time(returnArticleEntity.getCreated_time());
                }
            }
        }catch(Exception e) {
            System.out.println("입력하신 글의 내용이 비어있습니다.");
        }
        return returnArticle;
    }
    public article getArticleService(int articleIndex) {
        articleEntity article = articledao.getArticleEntity(articleIndex);
        article returnArticle = new article();
        returnArticle.setArticleIndex(article.getArticleIndex());
        returnArticle.setTitle(article.getTitle());
        returnArticle.setNickName(article.getNickname());
        returnArticle.setContents(article.getContents());
        returnArticle.setPassword(article.getPassword());
        returnArticle.setCreated_Time((article.getCreated_time()));


        return returnArticle;

    }

    public article putArticleService(article article) {
        articleEntity articleEntity = new articleEntity();
        article returnArticle = null;
        articleEntity returnArticleEntity = null;


        articleEntity.setArticleIndex(article.getArticleIndex());
        articleEntity.setTitle(article.getTitle());
        articleEntity.setNickname(article.getNickName());
        articleEntity.setContents(article.getContents());
        articleEntity.setPassword(article.getPassword());

        returnArticleEntity = articledao.putArticleEntity(articleEntity);

        if (returnArticleEntity != null) {
            returnArticle = new article();
            returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
            returnArticle.setTitle(returnArticleEntity.getTitle());
            returnArticle.setNickName(returnArticleEntity.getNickname());
            returnArticle.setContents(returnArticleEntity.getContents());
            returnArticle.setPassword(returnArticleEntity.getPassword());
            returnArticle.setCreated_Time(returnArticleEntity.getCreated_time());
            returnArticle.setUpdated_Time(returnArticleEntity.getUpdated_time());
        }
        else{
            System.out.println("수정하고자 하는 글이 존재하지 않습니다.");
        }


        return returnArticle;
    }

    public article deleteArticleService(int articleIndex,String password){
        article returnArticle = null;
        articleEntity returnArticleEntity = null;

        returnArticleEntity = articledao.deleteArticleEntity(articleIndex,password);

        if (returnArticleEntity != null) {
            returnArticle = new article();
            returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
            returnArticle.setTitle(returnArticleEntity.getTitle());
            returnArticle.setNickName(returnArticleEntity.getNickname());
            returnArticle.setContents(returnArticleEntity.getContents());
            returnArticle.setPassword(returnArticleEntity.getPassword());
            returnArticle.setCreated_Time(returnArticleEntity.getCreated_time());
        }

        return returnArticle;
    }



}
