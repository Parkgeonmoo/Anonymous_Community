package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,String> {
    List<CommentEntity> findByArticleIndex(String articleIndex);
    CommentEntity findByArticleIndexAndCommentIndex(String articleIndex, String commentIndex);
}
