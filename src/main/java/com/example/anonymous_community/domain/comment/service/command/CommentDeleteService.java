package com.example.anonymous_community.domain.comment.service.command;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.exception.ArticleErrorCode;
import com.example.anonymous_community.domain.article.exception.ArticleException;
import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import com.example.anonymous_community.domain.comment.dao.CommentDao;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentDeleteResponse;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 삭제 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentDeleteService {
    private final CommentDao commentdao;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 삭제
     *
     * @param articleIndex,commentIndex,password {@link Integer,Integer,String}
     */


    @Transactional
    public CommentDeleteResponse delete(Integer articleIndex,Integer commentIndex,String password) {

        if (articleIndex <= 0) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_BOUNDARY_ARTICLE_INDEX_ERROR);
        }

        if (!StringUtils.isNumeric(String.valueOf(articleIndex))) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_TYPE_ARTICLE_INDX_ERROR);
        }

        if (commentIndex <= 0) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_BOUNDARY_COMMENT_INDEX_ERROR);
        }

        if (!StringUtils.isNumeric(String.valueOf(commentIndex))) {
            throw new CommentException(CommentErrorCode.COMMENT_WRONG_TYPE_COMMENT_INDX_ERROR);
        }

        if (password == null) {
            throw new CommentException(CommentErrorCode.COMMENT_PASSWORD_NEED_ERROR);
        }

        articleRepository.findById(articleIndex)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_INDEX_DELETE_ERROR));

        CommentEntity commentEntity = commentRepository.findById(commentIndex)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_PUT_COMMENT_INDEX_FAIL_ERROR));

        if (!password.equals(commentEntity.getPassword())) {
            throw new CommentException(CommentErrorCode.COMMENT_UNVALID_PASSWORD_ERROR);
        }

        commentEntity = commentdao.delete(commentEntity);


        return CommentDeleteResponse.fromEntity(commentEntity);

    }

}
