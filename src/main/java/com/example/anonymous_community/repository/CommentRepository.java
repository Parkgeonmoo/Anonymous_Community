package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,String> {
    List<CommentEntity> findByArticleIndex(String articleIndex);
    CommentEntity findByArticleIndexAndCommentIndex(String articleIndex, String commentIndex);
}
