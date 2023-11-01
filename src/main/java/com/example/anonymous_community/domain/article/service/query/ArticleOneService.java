package com.example.anonymous_community.domain.article.service.query;

import com.example.anonymous_community.domain.article.dto.request.ArticleEntryRequest;
import com.example.anonymous_community.domain.article.dto.response.ArticleGetOneResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.repository.ArticleRepositorySupport;
import com.example.anonymous_community.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.anonymous_community.global.exception.ErrorCode.ARTICLE_GET_ERROR;

/**
 * 게시글 조회 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleOneService {

    private final ArticleRepositorySupport articleRepositorySupport;

    /**
     * 게시글 조회
     *
     * @param articleIndex 게시글 고유번호
     * @return {@link ArticleEntryRequest}
     */


    @Transactional(readOnly=true)
    public ArticleGetOneResponse getOne(Integer articleIndex) {
        ArticleEntity articleEntity = articleRepositorySupport.findByIndex(articleIndex)
                .orElseThrow(() -> new BaseException(ARTICLE_GET_ERROR));

        return ArticleGetOneResponse.fromEntity(articleEntity);
    }
}
