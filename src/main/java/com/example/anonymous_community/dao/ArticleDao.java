package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (articleEntity == null) {
            log.error("글쓰기 저장 실패입니다. 입력한 정보를 확인해 주세요.");
            return null;
        }
        try {
            return articleRepository.save(articleEntity);
        } catch (Exception e) {
            log.error("글쓰기 저장에 실패했습니다.");
            return null;
        }
    }

    /**
     * 게시글 수정
     *
     * @param articleEntity {@link ArticleEntity}
     */
    public ArticleEntity update(ArticleEntity articleEntity) {
        final Optional<ArticleEntity> temp = articleRepository.findById(articleEntity.getArticleIndex());
        if (temp.isPresent()) {
            final ArticleEntity entity = temp.get();
            if (StringUtils.equals(articleEntity.getPassword(), entity.getPassword())) {
                entity.setTitle(articleEntity.getTitle());
                entity.setNickname(articleEntity.getNickname());
                entity.setContents(articleEntity.getContents());
                entity.setPassword(articleEntity.getPassword());
                entity.setUpdatedTime(articleEntity.getUpdatedTime());
                return entity;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
    }

    /**
     * 게시글 삭제
     *
     * @param articleIndex 게시글 고유번호
     * @param password 게시글 암호
     */
    public ArticleEntity delete(String articleIndex, String password) {
        final Optional<ArticleEntity> temp = articleRepository.findById(articleIndex);
        if (temp.isPresent()) {
            final ArticleEntity articleEntity = temp.get();
            if (StringUtils.equals(articleEntity.getPassword(), password)) {
                articleRepository.delete(articleEntity);
                return articleEntity;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
    }
}
