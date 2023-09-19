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
        ArticleEntity ArticleEntity = new ArticleEntity();
        ArticleEntity returnArticleEntity = new ArticleEntity();
        Article returnArticle = new Article();



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

        ArticleEntity.setTitle(inputArticle.getTitle());
        ArticleEntity.setNickname(inputArticle.getNickName());
        ArticleEntity.setContents(inputArticle.getContents());
        ArticleEntity.setCreated_time(inputArticle.getCreatedTime());
        ArticleEntity.setPassword(inputArticle.getPassword());
        ArticleEntity.setUpdated_time(inputArticle.getUpdatedTime());
        returnArticleEntity = articledao.postArticleEntity(ArticleEntity);

        if (returnArticleEntity == null) {
            log.error("입력하신 글이 정상적으로 저장되지 않았습니다.");
            return null;
        }


        returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
        returnArticle.setTitle(returnArticleEntity.getTitle());
        returnArticle.setNickName(returnArticleEntity.getNickname());
        returnArticle.setContents(returnArticleEntity.getContents());
        returnArticle.setCreatedTime(returnArticleEntity.getCreated_time());
        returnArticle.setCreatedTime(returnArticleEntity.getUpdated_time());

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
            Article art = new Article();
            art.setArticleIndex(entity.getArticleIndex());
            art.setTitle(entity.getTitle());
            art.setNickName(entity.getNickname());
            art.setContents(entity.getContents());
            art.setPassword(entity.getPassword());
            art.setCreatedTime(entity.getCreated_time());
            art.setUpdatedTime(entity.getUpdated_time());
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
        Article returnArticle = new Article();

        if (ArticleEntity == null) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return null;
        }

        returnArticle.setArticleIndex(ArticleEntity.getArticleIndex());
        returnArticle.setTitle(ArticleEntity.getTitle());
        returnArticle.setNickName(ArticleEntity.getNickname());
        returnArticle.setContents(ArticleEntity.getContents());
        returnArticle.setPassword(ArticleEntity.getPassword());
        returnArticle.setCreatedTime((ArticleEntity.getCreated_time()));
        returnArticle.setUpdatedTime(ArticleEntity.getUpdated_time());


        return returnArticle;

    }

    public Article putArticleService(Article inputArticle) {
        ArticleEntity ArticleEntity = new ArticleEntity();
        Article returnArticle = null;
        ArticleEntity returnArticleEntity = null;

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



        ArticleEntity.setArticleIndex(inputArticle.getArticleIndex());
        ArticleEntity.setTitle(inputArticle.getTitle());
        ArticleEntity.setNickname(inputArticle.getNickName());
        ArticleEntity.setContents(inputArticle.getContents());
        ArticleEntity.setPassword(inputArticle.getPassword());
        ArticleEntity.setUpdated_time(inputArticle.getUpdatedTime());


        returnArticleEntity = articledao.putArticleEntity(ArticleEntity);

        if (returnArticleEntity == null) {
            log.error("수정하고자 하는 글이 존재하지 않습니다.");
            return null;
        }


        returnArticle = new Article();
        returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
        returnArticle.setTitle(returnArticleEntity.getTitle());
        returnArticle.setNickName(returnArticleEntity.getNickname());
        returnArticle.setContents(returnArticleEntity.getContents());
        returnArticle.setPassword(returnArticleEntity.getPassword());
        returnArticle.setCreatedTime(returnArticleEntity.getCreated_time());
        returnArticle.setUpdatedTime(returnArticleEntity.getUpdated_time());

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

        Article returnArticle = null;
        ArticleEntity returnArticleEntity = null;

        returnArticleEntity = articledao.deleteArticleEntity(articleIndex,password);

        if (returnArticleEntity == null) {
            log.error("지우고자 하는 글이 존재하지 않습니다.");
            return null;
        }

        if (returnArticleEntity != null) {
            returnArticle = new Article();
            returnArticle.setArticleIndex(returnArticleEntity.getArticleIndex());
            returnArticle.setTitle(returnArticleEntity.getTitle());
            returnArticle.setNickName(returnArticleEntity.getNickname());
            returnArticle.setContents(returnArticleEntity.getContents());
            returnArticle.setPassword(returnArticleEntity.getPassword());
            returnArticle.setCreatedTime(returnArticleEntity.getCreated_time());
            returnArticle.setUpdatedTime(returnArticleEntity.getUpdated_time());
        }

        return returnArticle;
    }



}
