package com.example.anonymous_community.domain.comment.dao;

import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.domain.comment.repository.CommentRepository;

import java.util.Optional;

/**
 * 댓글 Dao
 *
 * @author parkgeonwoo
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentDao {

    private final CommentRepository commentRepository;

    /**
     * 댓글 등록
     *
     * @param commentEntity {@link CommentEntity}
     * @return {@link CommentEntity}
     */
    public CommentEntity entry(CommentEntity commentEntity) {

        return commentRepository.save(commentEntity);
    }

    /**
     * 댓글 수정
     *
     * @param commentEntity {@link CommentEntity}
     * @return {@link CommentEntity}
     */


    public CommentEntity update(CommentEntity commentEntity) {

        return commentRepository.save(commentEntity);
    }



    /**
     * 댓글 삭제
     *
     * @param commentEntity {@link CommentEntity}
     * @return {@link CommentEntity}
     */

    /**
    public CommentEntity delete(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        final String articleIndex = commentEntity.getArticleIndex();
        final String commentIndex = commentEntity.getCommentIndex();

        // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
        final Optional<CommentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

        if (!existingCommentOptional.isPresent()) {
            log.error("해당하는 댓글이 존재하지 않습니다.");
            return null;
        }

        final CommentEntity existingComment = existingCommentOptional.get();

        // 비밀번호 일치 여부 확인
        if (!StringUtils.equals(existingComment.getPassword(), commentEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        commentRepository.delete(existingComment);
        return existingComment;

    }
     **/





}

