package com.example.anonymous_community.service.article.command;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.dao.ArticleDao;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dto.ArticleRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleEntryService {

    private final ArticleDao articledao;

    @Transactional
    public void postArticleService(ArticleRequest inputArticleRequest) {

        if (StringUtils.isBlank(inputArticleRequest.getTitle())) {
            log.error("입력하신 글의 제목이 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getNickName())) {
            log.error("입력하신 글의 닉네임이 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getContents())) {
            log.error("입력하신 글의 내용이 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getPassword())) {
            log.error("입력하신 글의 비밀번호가 비어있습니다.");
            return;
        }


        ArticleEntity returnArticleEntity = new ArticleEntity(inputArticleRequest);

        returnArticleEntity = articledao.postArticleEntity(returnArticleEntity);

        if (returnArticleEntity == null) {
            log.error("입력하신 글이 정상적으로 저장되지 않았습니다.");
            return;
        }


    }
}
