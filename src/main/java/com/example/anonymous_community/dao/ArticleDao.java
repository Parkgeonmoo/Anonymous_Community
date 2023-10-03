package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleDao {
    private final ArticleRepository articleRepository;


    public ArticleEntity postArticleEntity(ArticleEntity ArticleEntity) {
        if (ArticleEntity == null) {
            log.error("글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }

        try {
            ArticleEntity = articleRepository.save(ArticleEntity);
        }catch(Exception e) {
            log.error("글쓰기 저장 실패입니다.");
            return null;
        }

        return ArticleEntity;
    }
    public ArticleEntity getArticleEntity(String articleIndex){
        Optional<ArticleEntity> temp = articleRepository.findById(articleIndex);
        ArticleEntity ArticleEntity = null;
        if (temp.isPresent()) {
            ArticleEntity = temp.get();
        } else {
           log.error("해당 인덱스의 글이 존재하지 않습니다.");
           return null;
        }
        return ArticleEntity;

    }

    public Page<ArticleEntity> getArticlesEntity(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1, limit);
        return articleRepository.findAll(pageable);
    }

    public ArticleEntity putArticleEntity(ArticleEntity ArticleEntity) {
        Optional<ArticleEntity> temp = articleRepository.findById(ArticleEntity.getArticleIndex());
        ArticleEntity returnArticleEntity = null;
        if (temp.isPresent()) {
            ArticleEntity existingArticle = temp.get();
            if(ArticleEntity.getPassword().equals(existingArticle.getPassword())) {
                existingArticle.setTitle(ArticleEntity.getTitle());
                existingArticle.setNickname(ArticleEntity.getNickname());
                existingArticle.setContents(ArticleEntity.getContents());
                existingArticle.setPassword(ArticleEntity.getPassword());
                existingArticle.setUpdatedTime(ArticleEntity.getUpdatedTime());



            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return returnArticleEntity;
    }

    public ArticleEntity deleteArticleEntity(String articleIndex, String password){
        Optional<ArticleEntity> temp = articleRepository.findById(articleIndex);
        ArticleEntity ArticleEntity = null;
        if(temp.isPresent()) {
            ArticleEntity = temp.get();
            if(ArticleEntity.getPassword().equals(password)){
                articleRepository.delete(ArticleEntity);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return ArticleEntity;
    }

}
