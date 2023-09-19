package com.example.anonymous_community.service;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.dao.ArticleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dto.Article;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleEntryService {
    private final ArticleDao articledao;


    public Article postArticleService(Article inputArticle) {

        if (inputArticle.getTitle() == null) {
            log.error("입력하신 글의 제목이 비어있습니다.");
            return null;
        }

        if (inputArticle.getNickName() == null) {
            log.error("입력하신 글의 닉네임이 비어있습니다.");
            return null;
        }

        if (inputArticle.getContents() == null) {
            log.error("입력하신 글의 내용이 비어있습니다.");
            return null;
        }

        if (inputArticle.getPassword() == null) {
            log.error("입력하신 글의 비밀번호가 비어있습니다.");
            return null;
        }



        LocalDateTime now = LocalDateTime.now();

        inputArticle.setCreatedTime(now.toString());
        inputArticle.setUpdatedTime(now.toString());

        ArticleEntity returnArticleEntity = new ArticleEntity(inputArticle);

        returnArticleEntity = articledao.postArticleEntity(returnArticleEntity);

        if (returnArticleEntity == null) {
            log.error("입력하신 글이 정상적으로 저장되지 않았습니다.");
            return null;
        }
        Article returnArticle = new Article(returnArticleEntity);


        return returnArticle;
    }

    public List<Article> getArticlesService(int page, int limit) {

        if (page <= 0) {
            // 기본값 설정
            page = 1;
        }

        if (limit <= 0) {
            // 기본값 설정
            limit = 20;
        }


        Page<ArticleEntity> pageArticleEntity = articledao.getArticlesEntity(page,limit);
        List<Article> Articles = new ArrayList<>();

        for (ArticleEntity entity: pageArticleEntity.getContent()) {
            Article art = new Article(entity);
            Articles.add(art);
        }
        return Articles;
    }

    public Article getArticleService(String articleIndex) {

        int index;
        try {
            index = Integer.parseInt(articleIndex);
        } catch (NumberFormatException e) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        ArticleEntity ArticleEntity = articledao.getArticleEntity(articleIndex);


        if (ArticleEntity == null) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        Article returnArticle = new Article(ArticleEntity);

        return returnArticle;

    }

    public Article putArticleService(Article inputArticle) {

        int index;
        try {
            index = Integer.parseInt(inputArticle.getArticleIndex());
        } catch (NumberFormatException e) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        if (inputArticle.getPassword() == null) {
            log.error("비밀번호가 비어있습니다.");
            return null;
        }

        if (inputArticle.getTitle() == null && inputArticle.getNickName() == null && inputArticle.getContents() == null) {
            log.error("제목, 닉네임, 내용 중 적어도 하나는 입력되어야 합니다.");
            return null;
        }



        LocalDateTime now = LocalDateTime.now();
        inputArticle.setUpdatedTime(now.toString());

        ArticleEntity returnArticleEntity = new ArticleEntity(inputArticle);
        returnArticleEntity = articledao.putArticleEntity(returnArticleEntity);

        if (returnArticleEntity == null) {
            log.error("수정하고자 하는 글이 존재하지 않습니다.");
            return null;
        }

        Article returnArticle = new Article(returnArticleEntity);

        return returnArticle;
    }

    public Article deleteArticleService(String articleIndex, String password){

        int index;
        try {
            index = Integer.parseInt(articleIndex);
        } catch (NumberFormatException e) {
            log.error("해당 글을 지울 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 글을 지울 수 없습니다.");
            return null;
        }

        if (password == null || !password.matches("^[a-zA-Z0-9]{6,10}$")) {
            log.error("비밀번호가 유효하지 않습니다. 6-10자리의 영어 대소문자와 숫자만 가능합니다.");
            return null;
        }

        ArticleEntity returnArticleEntity = articledao.deleteArticleEntity(articleIndex,password);

        if (returnArticleEntity == null) {
            log.error("지우고자 하는 글이 존재하지 않습니다.");
            return null;
        }


        Article returnArticle = new Article(returnArticleEntity);


        return returnArticle;
    }



}
