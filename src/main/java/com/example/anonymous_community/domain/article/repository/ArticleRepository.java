package com.example.anonymous_community.domain.article.repository;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 게시글 Repository
 *
 * @author parkgeonwoo
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

}
