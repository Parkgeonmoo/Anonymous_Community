package com.example.anonymous_community.controller;

import com.example.anonymous_community.dto.article;
import com.example.anonymous_community.dto.comment;
import com.example.anonymous_community.service.articleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anonymous_community.service.commentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class restapi {
    articleService articleService = null;
    commentService commentService = null;


    public restapi(articleService articleservice,commentService commentService) {
        this.articleService = articleservice;
        this.commentService = commentService;
    }

    @PostMapping("/article")
    public ResponseEntity postArticle(@RequestBody article inputArticle){

        article result = articleService.postArticleService(inputArticle);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/comment")
    public ResponseEntity postComment(@RequestBody comment inputComment) {

        comment result = commentService.postCommentService(inputComment);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/articles")
    public ResponseEntity getArticles(@PageableDefault(page = 0,size = 20) int page, int limit) {
        List<article> result = articleService.getArticlesService(page,limit);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/article")
    public ResponseEntity getArticle(String articleindex){
        article result = articleService.getArticleService(articleindex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/comment")
    public ResponseEntity getComment(String articleIndex) {
        comment result = commentService.getCommentService(articleIndex);

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
    public ResponseEntity deleteArticle(String articleIndex,String password){
        article result = articleService.deleteArticleService(articleIndex,password);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
