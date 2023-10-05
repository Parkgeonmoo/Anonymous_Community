package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 게시글 Repository
 *
 * @author parkgeonwoo
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
}
