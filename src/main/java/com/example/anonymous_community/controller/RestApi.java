package com.example.anonymous_community.controller;

import com.example.anonymous_community.dto.Article;
import com.example.anonymous_community.dto.Comment;
import com.example.anonymous_community.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.anonymous_community.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApi {
    ArticleService articleService = null;
    CommentService commentService = null;


    public RestApi(ArticleService articleservice, CommentService commentService) {
        this.articleService = articleservice;
        this.commentService = commentService;
    }

    @PostMapping("/article")
    public ResponseEntity postArticle(@RequestBody Article inputArticle){

        Article result = articleService.postArticleService(inputArticle);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/comment")
    public ResponseEntity postComment(@RequestBody Comment inputComment) {

        Comment result = commentService.postCommentService(inputComment);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/articles")
    public ResponseEntity getArticles(int page, int limit) {
        List<Article> result = articleService.getArticlesService(page,limit);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/article")
    public ResponseEntity getArticle(String articleindex){
        Article result = articleService.getArticleService(articleindex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/comment")
    public ResponseEntity getComment(String articleIndex) {
        List<Comment> result = commentService.getCommentService(articleIndex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/article")
    public ResponseEntity putArticle(@RequestBody Article inputArticle){
        Article result = articleService.putArticleService(inputArticle);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/comment")
    public ResponseEntity putComment(@RequestBody Comment inputComment) {
        Comment result = commentService.putCommentService(inputComment);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/article")
    public ResponseEntity deleteArticle(String articleIndex,String password){
        Article result = articleService.deleteArticleService(articleIndex,password);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/comment")
    public ResponseEntity deleteComment(@RequestBody Comment inputComment) {
        Comment result = commentService.deleteCommentService(inputComment);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
