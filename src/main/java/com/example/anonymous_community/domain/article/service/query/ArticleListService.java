package com.example.anonymous_community.domain.article.service.query;

import com.example.anonymous_community.domain.article.dto.request.ArticleEntryRequest;
import com.example.anonymous_community.domain.article.dto.response.ArticleGetListResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.repository.ArticleRepositorySupport;
import com.example.anonymous_community.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.anonymous_community.global.exception.ErrorCode.ARTICLE_LIMIT_ERROR;
import static com.example.anonymous_community.global.exception.ErrorCode.ARTICLE_PAGE_ERROR;

/**
 * 게시글 목록 조회 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleListService {

    private final ArticleRepositorySupport articleRepositorySupport;

    /**
     * 게시글 목록 조회
     *
     * @param page 조회할 페이지
     * @param limit 페이지당 게시글 수
     * @return {@link List}
     */

    @Transactional(readOnly = true)
    public List<ArticleGetListResponse> getList(int page, int limit) {
        if (page <= 0) {
            throw new BaseException(ARTICLE_PAGE_ERROR);
        }
        if (limit <= 0) {
            throw new BaseException(ARTICLE_LIMIT_ERROR);
        }

        final List<ArticleEntity> articleEntities = articleRepositorySupport.findFirst(page, limit);

        return articleEntities.stream().map(ArticleGetListResponse::fromEntity).collect(Collectors.toList());

    }

}
