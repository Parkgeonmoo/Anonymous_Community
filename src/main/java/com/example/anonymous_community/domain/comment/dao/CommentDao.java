package com.example.anonymous_community.domain.comment.dao;

import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.domain.comment.repository.CommentRepository;

import javax.persistence.EntityManager;
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


    public CommentEntity delete(CommentEntity commentEntity) {

        commentRepository.delete(commentEntity);

        return commentEntity;
    }






}

