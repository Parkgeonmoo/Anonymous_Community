package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.entity.commentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentRepository extends JpaRepository<commentEntity,String> {
    List<commentEntity> findByArticleIndex(String articleIndex);
    commentEntity findByArticleIndexAndCommentIndex(String articleIndex, String commentIndex);
}
