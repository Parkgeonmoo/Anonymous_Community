package com.example.anonymous_community.controller.Impl;

import com.example.anonymous_community.controller.restapi;
import com.example.anonymous_community.dto.article;
import com.example.anonymous_community.service.Impl.articleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class restapiImpl implements restapi {
    articleServiceImpl articleServiceImpl = null;

    public restapiImpl(articleServiceImpl articleservice) {
        articleServiceImpl = articleservice;
    }

    @PostMapping("/article")
    public Boolean postArticel(String title,String nickname,String contents,String password){
       boolean result = articleServiceImpl.postArticleService(title,nickname,contents,password);
       return result;
    }

    @GetMapping("/article")
    public article getArticle(int articleindex){
        article article = articleServiceImpl.getArticleService(articleindex);
        return article;
    }

    @PutMapping("/article")
    public void putArticle(int articelsIndex,String title,String nickname,String contents,String password,String newpassword){
        articleServiceImpl.putArticleService(articelsIndex,title,nickname,contents,password,newpassword);
    }

    @DeleteMapping("/article")
    public void deleteArticle(int articleIndex,String password){
        articleServiceImpl.deleteArticleService(articleIndex,password);
    }

}
