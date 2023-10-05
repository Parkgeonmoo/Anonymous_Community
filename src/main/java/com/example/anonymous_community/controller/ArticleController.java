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

/**
 * 게시글 controller
 *
 * @author parkgeonwoo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleEntryService articleEntryService;
    private final ArticleUpdateService articleUpdateService;
    private final ArticleOneService articleOneService;
    private final ArticleListService articleListService;
    private final ArticleDeleteService articleDeleteService;

    /**
     * 게시글 등록 요청
     *
     * @param param {@link ArticleRequest} 게시글 등록 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PostMapping("/article")
    public ResponseEntity doPostASArticle(@RequestBody ArticleRequest param) {
        try {
            articleEntryService.entry(param);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 게시글 목록 조회 요청
     *
     * @param page 조회 페이지
     * @param limit 페이지당 게시글 수
     * @return {@link ResponseEntity}
     */
    @GetMapping("/articles")
    public ResponseEntity doGetAsArticles(int page, int limit) {
        final List<ArticleRequest> result = articleListService.getList(page, limit);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 게시글 단건 조회 요청
     *
     * @param articleIndex 게시글 고유번호
     * @return {@link ResponseEntity}
     */
    @GetMapping("/article")
    public ResponseEntity doGetAsArticle(String articleIndex) {
        final ArticleRequest result = articleOneService.getOne(articleIndex);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 게시글 수정 요청
     *
     * @param param {@link ArticleRequest} 게시글 수정 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PutMapping("/article")
    public ResponseEntity doPutAsArticle(@RequestBody ArticleRequest param) {
        try {
            articleUpdateService.putArticleService(param);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 게시글 삭제 요청
     * @param articleIndex 게시글 고유번호
     * @param password 게시글 암호
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/article")
    public ResponseEntity doDeleteAsArticle(String articleIndex, String password) {
        try {
            articleDeleteService.delete(articleIndex, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


