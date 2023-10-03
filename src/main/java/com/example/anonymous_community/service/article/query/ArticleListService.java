package com.example.anonymous_community.service.article.query;

import com.example.anonymous_community.dao.ArticleDao;
import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleListService {

    private final ArticleDao articledao;

    @Transactional(readOnly = true)
    public List<ArticleRequest> getArticlesService(int page, int limit) {

        if (page <= 0) {
            // 기본값 설정
            page = 1;
        }

        if (limit <= 0) {
            // 기본값 설정
            limit = 20;
        }


        Page<ArticleEntity> pageArticleEntity = articledao.getArticlesEntity(page,limit);
        List<ArticleRequest> articleRequests = new ArrayList<>();

        for (ArticleEntity entity: pageArticleEntity.getContent()) {
            ArticleRequest art = new ArticleRequest(entity);
            articleRequests.add(art);
        }
        return articleRequests;
    }
}