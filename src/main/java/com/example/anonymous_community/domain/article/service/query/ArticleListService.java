package com.example.anonymous_community.domain.article.service.query;

import com.example.anonymous_community.domain.article.dto.response.ArticleGetListResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.repository.ArticleRepositorySupport;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;



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

        if (!StringUtils.isNumeric(String.valueOf(page))) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_PAGE_WRONG_TYE_ERROR);
        }

        if (!StringUtils.isNumeric(String.valueOf(limit))) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_LIMIT_WRONG_TYE_ERROR);
        }

        if (page <= 0) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_PAGE_ERROR);
        }
        if (limit <= 0) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_LIMIT_ERROR);
        }

        final List<ArticleEntity> articleEntities = articleRepositorySupport.findFirst(page, limit);

        return articleEntities.stream().map(ArticleGetListResponse::fromEntity).collect(Collectors.toList());

    }

}
