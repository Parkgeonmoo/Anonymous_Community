package com.example.anonymous_community.domain.article.dao;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 게시글 Dao
 *
 * @author parkgeonwoo
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleDao {
    private final ArticleRepository articleRepository;

    /**
     * 게시글 저장
     *
     * @param articleEntity {@link ArticleEntity}
     * @return {@link ArticleEntity}
     */
    public ArticleEntity entry(ArticleEntity articleEntity) {

        return articleRepository.save(articleEntity);

    }

    /**
     * 게시글 수정
     *
     * @param articleEntity {@link ArticleEntity}
     */
    public ArticleEntity update(ArticleEntity articleEntity) {

        return articleRepository.save(articleEntity);


    }

    /**
     * 게시글 삭제
     *
     * @param articleEntity 게시글
     */
    public ArticleEntity delete(ArticleEntity articleEntity) {

        articleRepository.delete(articleEntity);
        return articleEntity;

    }
}
