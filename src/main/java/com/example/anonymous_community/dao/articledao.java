package com.example.anonymous_community.dao;

import com.example.anonymous_community.dto.article;
import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.repository.articleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class articledao {
    articleRepository articleRepository;

    public articledao(articleRepository repository) {
        this.articleRepository = repository;
    }

    public articleEntity postArticleEntity(articleEntity article){
        if (article == null) {
            System.out.println("글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }

        try {
            articleRepository.save(article);
        }catch(Exception e) {
            System.out.println("글쓰기 저장 실패입니다.");
            return null;
        }

        return article;
    }
    public articleEntity getArticleEntity(int articleIndex){
        Optional<articleEntity> temp = articleRepository.findById(articleIndex);
        articleEntity article = null;
        if (temp.isPresent()) {
            article = temp.get();
        }else{
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return article;

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


                returnArticleEntity = articleRepository.save(existingArticle);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
        return returnArticleEntity;
    }

    public articleEntity deleteArticleEntity(int articleIndex, String password){
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
