package com.example.anonymous_community.service.article.query;

import com.example.anonymous_community.dto.request.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.repository.ArticleRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @return {@link ArticleRequest}
     */
    @Transactional(readOnly=true)
    public ArticleRequest getOne(String articleIndex) {

        if (!NumberUtils.isDigits(articleIndex)){
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        final int index = NumberUtils.toInt(articleIndex);
        if (index <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        final ArticleEntity ArticleEntity = articleRepositorySupport.findByIndex(articleIndex);
        if (ArticleEntity == null) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        return new ArticleRequest(ArticleEntity);

    }
}
