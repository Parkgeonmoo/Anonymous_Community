package com.example.anonymous_community.domain.comment.service.command;

import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import com.example.anonymous_community.domain.comment.dao.CommentDao;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.request.CommentUpdateRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentUpdateResponse;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.domain.comment.exception.CommentErrorCode;
import com.example.anonymous_community.domain.comment.exception.CommentException;
import com.example.anonymous_community.domain.comment.repository.CommentRepository;
import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 댓글 수정 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentUpdateService {
    private final CommentDao commentdao;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 수정
     *
     * @param inputCommentUpdateRequest {@link CommentEntryRequest}
     */


    @Transactional
    public CommentUpdateResponse putCommentService(CommentUpdateRequest inputCommentUpdateRequest) {

        final Integer articleIndex = inputCommentUpdateRequest.getArticleIndex();
        final Integer commentIndex = inputCommentUpdateRequest.getCommentIndex();
        final String password = inputCommentUpdateRequest.getPassword();



        articleRepository.findById(articleIndex)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_PUT_ARTICLE_INDEX_FAIL_ERROR));
        final Optional<CommentEntity> optionalCommentEntity = Optional.ofNullable(commentRepository.findById(commentIndex)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_PUT_COMMENT_INDEX_FAIL_ERROR)));

        CommentEntity commentEntity = optionalCommentEntity.get();

        if (!password.equals(commentEntity.getPassword())) {
            throw new CommentException(CommentErrorCode.COMMENT_UNVALID_PASSWORD_ERROR);
        }

        commentEntity.setAllColumns(inputCommentUpdateRequest);


        final CommentEntity returnCommentEntity = commentdao.update(commentEntity);

        return CommentUpdateResponse.fromEntity(returnCommentEntity);
    }

}
