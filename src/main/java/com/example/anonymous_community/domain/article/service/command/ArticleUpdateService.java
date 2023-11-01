package com.example.anonymous_community.domain.article.service.command;

import com.example.anonymous_community.domain.article.dao.ArticleDao;
import com.example.anonymous_community.domain.article.dto.request.ArticleUpdateRequest;
import com.example.anonymous_community.domain.article.dto.response.ArticleUpdateResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import com.example.anonymous_community.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 수정 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleUpdateService {

    private final ArticleDao articledao;
    private final ArticleRepository articleRepository;

    /**
     * 게시글 수정
     *
     * @param inputArticleUpdateRequest {@link ArticleUpdateRequest}
     */


    @Transactional
    public ArticleUpdateResponse putArticleService(ArticleUpdateRequest inputArticleUpdateRequest) {

        ArticleEntity articleEntity = articleRepository.findById(inputArticleUpdateRequest.getArticleIndex())
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_PUT_FIND_ERROR));

        if (!(inputArticleUpdateRequest.getPassword().equals(articleEntity.getPassword()))) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_PASSWORD_NO_MATCH_ERROR);
        }

        articleEntity.setAllColumns(inputArticleUpdateRequest);


        final ArticleEntity returnArticleEntity = articledao.update(articleEntity);

        return ArticleUpdateResponse.fromEntity(returnArticleEntity);

    }

}
