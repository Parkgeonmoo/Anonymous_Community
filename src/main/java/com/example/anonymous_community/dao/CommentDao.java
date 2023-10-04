package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.repository.CommentRepository;

import java.util.List;
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
        if (commentEntity == null) {
            System.out.println("댓글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }
        try {
            return commentRepository.save(commentEntity);
        } catch(Exception e) {
            log.error("댓글쓰기 저장 실패입니다.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 댓글 수정
     *
     * @param commentEntity {@link CommentEntity}
     * @return {@link CommentEntity}
     */
    public CommentEntity update(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }

        final String articleIndex = commentEntity.getArticleIndex();
        final String commentIndex = commentEntity.getCommentIndex();

        // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
        final Optional<CommentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

        if (!existingCommentOptional.isPresent()) {
            log.error("해당하는 댓글이 존재하지 않습니다.");
        }

        final CommentEntity storedComment = existingCommentOptional.get();

        // 비밀번호 일치 여부 확인
        if (!StringUtils.equals(storedComment.getPassword(), commentEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 업데이트할 내용 설정
        storedComment.setContents(commentEntity.getContents());

        // 업데이트된 댓글 저장
        return commentRepository.save(storedComment);
    }

    /**
     * 댓글 삭제
     *
     * @param commentEntity {@link CommentEntity}
     * @return {@link CommentEntity}
     */
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





}

