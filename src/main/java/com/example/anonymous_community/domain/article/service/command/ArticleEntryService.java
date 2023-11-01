package com.example.anonymous_community.domain.article.service.command;

import com.example.anonymous_community.domain.article.dto.response.ArticleEntryResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.dao.ArticleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.domain.article.dto.request.ArticleEntryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 등록 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleEntryService {

    private final ArticleDao articledao;


    /**
     * 게시글 등록
     *
     * @param inputArticleEntryRequest {@link ArticleEntryRequest}
     */
    @Transactional
    public ArticleEntryResponse entry(ArticleEntryRequest inputArticleEntryRequest) {

        final ArticleEntity articleEntity = ArticleEntity.builder()
                .title(inputArticleEntryRequest.getTitle())
                .nickname(inputArticleEntryRequest.getNickName())
                .contents(inputArticleEntryRequest.getContents())
                .password(inputArticleEntryRequest.getPassword())
                .build();


        final ArticleEntity returnArticleEntity = articledao.entry(articleEntity);

        return ArticleEntryResponse.fromEntity(returnArticleEntity);
    }
}
