package com.example.anonymous_community.service.article.query;

import com.example.anonymous_community.dto.request.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.repository.ArticleRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

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
    public List<ArticleRequest> getList(int page, int limit) {
        if (page <= 0) {
            page = 1;
        }
        if (limit <= 0) {
            limit = 20;
        }
        final List<ArticleEntity> articleEntities = articleRepositorySupport.findFirst(page, limit);
        final List<ArticleRequest> articleRequests = new ArrayList<>();
        for (ArticleEntity entity : articleEntities) {
            articleRequests.add(new ArticleRequest(entity));
        }
        return articleRequests;
    }
}
