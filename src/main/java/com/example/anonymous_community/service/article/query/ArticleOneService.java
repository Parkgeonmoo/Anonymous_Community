package com.example.anonymous_community.service.article.query;

import com.example.anonymous_community.dao.ArticleDao;
import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.repository.ArticleRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleOneService {

    private final ArticleRepositorySupport articleRepositorySupport;

    @Transactional(readOnly=true)
    public ArticleRequest getArticleService(String articleIndex) {

        int index;
        try {
            index = Integer.parseInt(articleIndex);
        } catch (NumberFormatException e) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }


        ArticleEntity ArticleEntity = articleRepositorySupport.findByIndex(articleIndex);


        if (ArticleEntity == null) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        ArticleRequest returnArticleRequest = new ArticleRequest(ArticleEntity);

        return returnArticleRequest;

    }
}
