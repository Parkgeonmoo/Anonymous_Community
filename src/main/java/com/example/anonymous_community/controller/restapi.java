package com.example.anonymous_community.controller;

import com.example.anonymous_community.dto.article;
import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.service.articleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class restapi {
    articleService articleService = null;

    public restapi(articleService articleservice) {
        articleService = articleservice;
    }

    @PostMapping("/article")
    public ResponseEntity postArticel(@RequestBody article inputArticle){

        article result = articleService.postArticleService(inputArticle);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/article")
    public ResponseEntity getArticle(int articleindex){
        article result = articleService.getArticleService(articleindex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/article")
    public ResponseEntity putArticle(@RequestBody article inputArticle){
        article result = articleService.putArticleService(inputArticle);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/article")
    public ResponseEntity deleteArticle(int articleIndex,String password){
        article result = articleService.deleteArticleService(articleIndex,password);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
