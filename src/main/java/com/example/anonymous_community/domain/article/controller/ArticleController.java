package com.example.anonymous_community.domain.article.controller;

import com.example.anonymous_community.domain.article.dto.request.ArticleEntryRequest;
import com.example.anonymous_community.domain.article.dto.request.ArticleUpdateRequest;
import com.example.anonymous_community.domain.article.dto.response.*;
import com.example.anonymous_community.global.common.ApiResponse;
import com.example.anonymous_community.domain.article.service.command.ArticleDeleteService;
import com.example.anonymous_community.domain.article.service.command.ArticleEntryService;
import com.example.anonymous_community.domain.article.service.command.ArticleUpdateService;
import com.example.anonymous_community.domain.article.service.query.ArticleListService;
import com.example.anonymous_community.domain.article.service.query.ArticleOneService;
import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
     * @param articleEntryRequest {@link ArticleEntryRequest} 게시글 등록 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PostMapping("/article")
    public ResponseEntity<ApiResponse> doPostASArticle(@Valid @RequestBody ArticleEntryRequest articleEntryRequest) {
        ArticleEntryResponse articleEntryResponse = articleEntryService.entry(articleEntryRequest);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(articleEntryResponse)
                        .build()
        );

    }

    /**
     * 게시글 목록 조회 요청
     *
     * @param page 조회 페이지
     * @param limit 페이지당 게시글 수
     * @return {@link ResponseEntity}
     */

    @GetMapping("/articles")
    public ResponseEntity<ApiResponse> doGetAsArticles(@Valid @RequestParam("page") int page, @RequestParam("limit") int limit) {

        final List<ArticleGetListResponse> result = articleListService.getList(page, limit);


        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(result)
                        .build()
        );

    }



    /**
     * 게시글 단건 조회 요청
     *
     * @param articleIndex 게시글 고유번호
     * @return {@link ResponseEntity}
     */

    @GetMapping("/article")
    public ResponseEntity<ApiResponse> doGetAsArticle(@Valid @RequestParam("articleIndex") Integer articleIndex) {

        final ArticleGetOneResponse result = articleOneService.getOne(articleIndex);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(result)
                        .build()
        );

    }


    /**
     * 게시글 수정 요청
     *
     * @param articleUpdateRequest {@link ArticleUpdateRequest} 게시글 수정 요청 파라미터
     * @return {@link ResponseEntity}
     */

    @PutMapping("/article")
    public ResponseEntity<ApiResponse> doPutAsArticle(@Valid @RequestBody ArticleUpdateRequest articleUpdateRequest) {

        System.out.println(articleUpdateRequest.getTitle());
        System.out.println(articleUpdateRequest.getContents());
        System.out.println(articleUpdateRequest.getNickName());

        ArticleUpdateResponse articleUpdateResponse = articleUpdateService.putArticleService(articleUpdateRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(articleUpdateResponse)
                        .build()
        );

    }


    /**
     * 게시글 삭제 요청
     * @param articleIndex 게시글 고유번호
     * @param password 게시글 암호
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/article")
    public ResponseEntity<ApiResponse> doDeleteAsArticle(@Valid @RequestParam("articleIndex") Integer articleIndex, @RequestParam("password") String password) {

        ArticleDeleteResponse articleDeleteResponse = articleDeleteService.delete(articleIndex, password);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(articleDeleteResponse)
                        .build()
        );



    }
}


