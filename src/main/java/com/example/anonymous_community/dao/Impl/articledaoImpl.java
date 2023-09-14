package com.example.anonymous_community.dao.Impl;

import com.example.anonymous_community.dao.articledao;
import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.repository.articleRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class articledaoImpl implements articledao {
    articleRepository articleRepository;

    public articledaoImpl(articleRepository repository) {
        this.articleRepository = repository;
    }

    public boolean postArticleEntity(articleEntity article){

        try {
            articleRepository.save(article);
            return true;

        }catch(Exception e) {
           return false;
        }
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
    public void putArticleEntity(int articleIndex, articleEntity article,String newpassword){
        Optional<articleEntity> temp = articleRepository.findById(articleIndex);

        if (temp.isPresent()) {
            articleEntity existingArticle = temp.get();
            if(article.getPassword().equals(existingArticle.getPassword())) {
                existingArticle.setTitle(article.getTitle());
                existingArticle.setNickname(article.getNickname());
                existingArticle.setContents(article.getContents());
                existingArticle.setPassword(newpassword);

                articleRepository.save(existingArticle);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
    }

    public void deleteArticleEntity(int articleIndex, String password){
        Optional<articleEntity> temp = articleRepository.findById(articleIndex);

        if(temp.isPresent()){
            articleEntity article = temp.get();
            if(article.getPassword().equals(password)){
                articleRepository.delete(article);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new NoSuchElementException("해당 인덱스의 글이 존재하지 않습니다.");
        }
    }

}
