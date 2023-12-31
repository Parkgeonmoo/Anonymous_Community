package com.example.anonymous_community.domain.article.service.command;

import com.example.anonymous_community.domain.article.dao.ArticleDao;
import com.example.anonymous_community.domain.article.dto.response.ArticleDeleteResponse;
import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 삭제 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleDeleteService {

    private final ArticleDao articledao;
    private final ArticleRepository articleRepository;

     /**
      * 게시글 삭제
      *
      * @param articleIndex 게시글 고유번호
      * @param password 게시글 비밀번호
      */
    @Transactional
    public ArticleDeleteResponse delete(Integer articleIndex, String password) {

        if (articleIndex <= 0) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR);
        }

        if (!StringUtils.isNumeric(String.valueOf(articleIndex))) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_WRONG_TYPE_ARTICLE_INDX_ERROR);
        }

        if (password == null) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_PASSWORD_NEED_ERROR);
        }


        ArticleEntity articleEntity = articleRepository.findById(articleIndex)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_INDEX_DELETE_ERROR));

        if (!password.equals(articleEntity.getPassword())) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_PASSWORD_NO_MATCH_ERROR);
        }

        articleEntity = articledao.delete(articleEntity);

        return ArticleDeleteResponse.fromEntity(articleEntity);

    }
}
