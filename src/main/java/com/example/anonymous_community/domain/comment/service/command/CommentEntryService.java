package com.example.anonymous_community.domain.comment.service.command;

import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentEntryResponse;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.domain.comment.repository.CommentRepository;
import com.example.anonymous_community.global.exception.BaseException;
import com.example.anonymous_community.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.example.anonymous_community.domain.comment.dao.CommentDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.anonymous_community.global.exception.ErrorCode.COMMENT_ENTRY_ARTICLE_INDEX_FAIL_ERROR;

/**
 * 댓글 등록 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentEntryService {
    private final CommentDao commentdao;
    private final ArticleRepository articleRepository;

    /**
     * 댓글 등록
     *
     * @param inputCommentEntryRequest {@link CommentEntryRequest}
     */
    @Transactional
    public CommentEntryResponse entry(CommentEntryRequest inputCommentEntryRequest) {
        System.out.println(inputCommentEntryRequest.getArticleIndex());
        articleRepository.findById(inputCommentEntryRequest.getArticleIndex())
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_ENTRY_ARTICLE_INDEX_FAIL_ERROR));


         CommentEntity commentEntity = CommentEntity.builder()
                .articleIndex(inputCommentEntryRequest.getArticleIndex())
                .nickName(inputCommentEntryRequest.getNickName())
                .contents(inputCommentEntryRequest.getContents())
                .password(inputCommentEntryRequest.getPassword())
                .build();


        final CommentEntity returnCommentEntity = commentdao.entry(commentEntity);

        return CommentEntryResponse.fromEntity(returnCommentEntity);

    }
}
