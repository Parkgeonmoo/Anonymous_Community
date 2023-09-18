package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@Slf4j
public class ArticleDao {
    ArticleRepository articleRepository;

    public ArticleDao(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public articleEntity postArticleEntity(articleEntity articleEntity){
        if (articleEntity == null) {
            System.out.println("글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }

        try {

            articleEntity = articleRepository.save(articleEntity);
        }catch(Exception e) {
            System.out.println("글쓰기 저장 실패입니다.");
            return null;
        }

        return articleEntity;
    }
    public articleEntity getArticleEntity(String articleIndex){
        Optional<articleEntity> temp = articleRepository.findById(articleIndex);
        articleEntity articleEntity = null;
        if (temp.isPresent()) {
            articleEntity = temp.get();
        }else{
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return articleEntity;

    }

    public Page<articleEntity> getArticlesEntity(int page, int limit){
        Pageable pageable = PageRequest.of(page-1, limit);
        return articleRepository.findAll(pageable);
    }

    public articleEntity putArticleEntity(articleEntity articleEntity){
        Optional<articleEntity> temp = articleRepository.findById(articleEntity.getArticleIndex());
        articleEntity returnArticleEntity = null;
        if (temp.isPresent()) {
            articleEntity existingArticle = temp.get();
            if(articleEntity.getPassword().equals(existingArticle.getPassword())) {
                existingArticle.setTitle(articleEntity.getTitle());
                existingArticle.setNickname(articleEntity.getNickname());
                existingArticle.setContents(articleEntity.getContents());
                existingArticle.setPassword(articleEntity.getPassword());
                existingArticle.setUpdated_time(articleEntity.getUpdated_time());


                returnArticleEntity = articleRepository.save(existingArticle);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return returnArticleEntity;
    }

    public articleEntity deleteArticleEntity(String articleIndex, String password){
        Optional<articleEntity> temp = articleRepository.findById(articleIndex);
        articleEntity articleEntity = null;
        if(temp.isPresent()){
            articleEntity = temp.get();
            if(articleEntity.getPassword().equals(password)){
                articleRepository.delete(articleEntity);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return articleEntity;
    }

}
