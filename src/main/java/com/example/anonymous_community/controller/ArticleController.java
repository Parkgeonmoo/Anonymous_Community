package com.example.anonymous_community.controller;


import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.service.article.command.ArticleDeleteService;
import com.example.anonymous_community.service.article.command.ArticleEntryService;
import com.example.anonymous_community.service.article.command.ArticleUpdateService;
import com.example.anonymous_community.service.article.query.ArticleListService;
import com.example.anonymous_community.service.article.query.ArticleOneService;
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
    private final ArticleUpdateService articleUpdateService;
    private final ArticleOneService articleOneService;
    private final ArticleListService articleListService;
    private final ArticleDeleteService articleDeleteService;



    @PostMapping("/article")
    public ResponseEntity doPostASArticle(@RequestBody ArticleRequest inputArticleRequest) {

        try {
            articleEntryService.postArticleService(inputArticleRequest);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @GetMapping("/articles")
    public ResponseEntity doGetAsArticles(int page, int limit) {
        List<ArticleRequest> result = articleListService.getArticlesService(page,limit);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/article")
    public ResponseEntity doGetAsArticle(String articleindex) {
        ArticleRequest result = articleOneService.getArticleService(articleindex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/article")
    public ResponseEntity doPutAsArticle(@RequestBody ArticleRequest inputArticleRequest) {
        try {
            articleUpdateService.putArticleService(inputArticleRequest);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/article")
    public ResponseEntity doDeleteAsArticle(String articleIndex,String password) {
        try {
            articleDeleteService.deleteArticleService(articleIndex, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


