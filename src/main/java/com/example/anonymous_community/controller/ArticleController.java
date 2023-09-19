package com.example.anonymous_community.controller;


import com.example.anonymous_community.dto.Article;
import com.example.anonymous_community.service.ArticleEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleEntryService articleEntryService;


    @PostMapping("/article")
    public ResponseEntity doPostASArticle(@RequestBody Article inputArticle) {

        Article result = articleEntryService.postArticleService(inputArticle);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/articles")
    public ResponseEntity doGetAsArticles(int page, int limit) {
        List<Article> result = articleEntryService.getArticlesService(page,limit);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/article")
    public ResponseEntity doGetAsArticle(String articleindex) {
        Article result = articleEntryService.getArticleService(articleindex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/article")
    public ResponseEntity doPutAsArticle(@RequestBody Article inputArticle) {
        Article result = articleEntryService.putArticleService(inputArticle);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/article")
    public ResponseEntity doDeleteAsArticle(String articleIndex,String password) {
        Article result = articleEntryService.deleteArticleService(articleIndex,password);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
