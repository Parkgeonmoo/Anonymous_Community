package com.example.anonymous_community.domain.comment.repository;

import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 댓글 Repository
 *
 * @author parkgeonwoo
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}
