package com.example.anonymous_community.service;

import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.dao.articledao;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dto.article;
import java.util.*;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class articleService {
   articledao articledao = null;

    public articleService(articledao articledao) {
        this.articledao = articledao;
    }

    public article postArticleService(article inputArticle) {
        articleEntity articleEntity = new articleEntity();
        articleEntity returnArticleEntity = new articleEntity();
        article returnArticle = new article();
        try {
            if (inputArticle != null) {
                LocalDateTime now = LocalDateTime.now();

                inputArticle.setCreated_Time(now.toString());
                inputArticle.setUpdated_Time(now.toString());

                articleEntity.setTitle(inputArticle.getTitle());
                articleEntity.setNickname(inputArticle.getNickName());
                articleEntity.setContents(inputArticle.getContents());
                articleEntity.setPassword(inputArticle.getPassword());
                articleEntity.setCreated_time(inputArticle.getCreated_Time());
                articleEntity.setUpdated_time(inputArticle.getUpdated_Time());
                returnArticleEntity = articledao.postArticleEntity(articleEntity);

                if (returnArticleEntity != null) {
                    returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
                    returnArticle.setTitle(returnArticleEntity.getTitle());
                    returnArticle.setNickName(returnArticleEntity.getNickname());
                    returnArticle.setContents(returnArticleEntity.getContents());
                    returnArticle.setPassword(returnArticleEntity.getPassword());
                    returnArticle.setCreated_Time(returnArticleEntity.getCreated_time());
                    returnArticle.setCreated_Time(returnArticleEntity.getUpdated_time());
                }
            }
        }catch(Exception e) {
            System.out.println("입력하신 글의 내용이 비어있습니다.");
        }
        return returnArticle;
    }

    public List<article> getArticlesService(int page,int limit) {
         Page<articleEntity> pageArticleEntity = articledao.getArticlesEntity(page,limit);
         List<article> articles = new ArrayList<>();
         for (articleEntity entity: pageArticleEntity.getContent()) {
             article art = new article();
             art.setArticleIndex(entity.getArticleIndex());
             art.setTitle(entity.getTitle());
             art.setNickName(entity.getNickname());
             art.setContents(entity.getContents());
             art.setPassword(entity.getPassword());
             art.setCreated_Time(entity.getCreated_time());
             art.setUpdated_Time(entity.getUpdated_time());
             articles.add(art);
         }
         return articles;
    }
    public article getArticleService(String articleIndex) {
        articleEntity articleEntity = articledao.getArticleEntity(articleIndex);
        article returnArticle = new article();
        returnArticle.setArticleIndex(articleEntity.getArticleIndex());
        returnArticle.setTitle(articleEntity.getTitle());
        returnArticle.setNickName(articleEntity.getNickname());
        returnArticle.setContents(articleEntity.getContents());
        returnArticle.setPassword(articleEntity.getPassword());
        returnArticle.setCreated_Time((articleEntity.getCreated_time()));
        returnArticle.setUpdated_Time(articleEntity.getUpdated_time());


        return returnArticle;

    }

    public article putArticleService(article inputArticle) {
        articleEntity articleEntity = new articleEntity();
        article returnArticle = null;
        articleEntity returnArticleEntity = null;

        try {
            if (inputArticle != null) {
                LocalDateTime now = LocalDateTime.now();
                inputArticle.setUpdated_Time(now.toString());

                articleEntity.setArticleIndex(inputArticle.getArticleIndex());
                articleEntity.setTitle(inputArticle.getTitle());
                articleEntity.setNickname(inputArticle.getNickName());
                articleEntity.setContents(inputArticle.getContents());
                articleEntity.setPassword(inputArticle.getPassword());
                articleEntity.setUpdated_time(inputArticle.getUpdated_Time());
            } else {
                System.out.println("글을 수정하기 위해 필요한 내용을 작성해주세요.");
            }

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
            } else {
                System.out.println("수정하고자 하는 글이 존재하지 않습니다.");
            }
        }catch(Exception e) {
            log.error("글 수정 에러");
        }


        return returnArticle;
    }

    public article deleteArticleService(String articleIndex,String password){
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
            returnArticle.setUpdated_Time(returnArticleEntity.getUpdated_time());
        }

        return returnArticle;
    }



}
