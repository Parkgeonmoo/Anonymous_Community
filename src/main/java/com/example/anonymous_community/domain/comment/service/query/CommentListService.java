package com.example.anonymous_community.domain.comment.service.query;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.repository.ArticleRepositorySupport;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentGetListResponse;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.domain.comment.repository.CommentRepositoySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 댓글 목록 조회 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentListService {
    private final CommentRepositoySupport commentRepositoySupport;
    private final ArticleRepositorySupport articleRepositorySupport;

    /**
     * 댓글 목록 조회
     * @param articleIndex 게시글 고유번호
     * @return {@link List}
     */
    //
    @Transactional(readOnly = true)
    public List<CommentGetListResponse> getList(Integer articleIndex) {

        if (articleIndex <= 0) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR);
        }

        if (!StringUtils.isNumeric(String.valueOf(articleIndex))) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_TYPE_ARTICLE_INDX_ERROR);
        }

        ArticleEntity articleEntity = articleRepositorySupport.findByIndex(articleIndex)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_GET_ERROR));

        List<CommentEntity> commentEntities = articleEntity.getComments();

        List<CommentGetListResponse> commentResponses = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentGetListResponse commentResponse = CommentGetListResponse.fromEntity(commentEntity);
            commentResponses.add(commentResponse);
        }

        return commentResponses;
    }

}
