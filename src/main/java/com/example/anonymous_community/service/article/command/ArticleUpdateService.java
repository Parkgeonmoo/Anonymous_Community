package com.example.anonymous_community.service.article.command;

import com.example.anonymous_community.dao.ArticleDao;
import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleUpdateService {

    private final ArticleDao articledao;


    @Transactional
    public void putArticleService(ArticleRequest inputArticleRequest) {

        int index;
        try {
            index = Integer.parseInt(inputArticleRequest.getArticleIndex());
        } catch (NumberFormatException e) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return;
        }

        if (index <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getPassword())) {
            log.error("비밀번호가 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getTitle()) && StringUtils.isBlank(inputArticleRequest.getNickName()) && StringUtils.isBlank(inputArticleRequest.getContents())) {
            log.error("제목, 닉네임, 내용 중 적어도 하나는 입력되어야 합니다.");
            return;
        }


        ArticleEntity returnArticleEntity = new ArticleEntity(inputArticleRequest);
        returnArticleEntity = articledao.putArticleEntity(returnArticleEntity);

        if (returnArticleEntity == null) {
            log.error("수정하고자 하는 글이 존재하지 않습니다.");
            return;
        }
    }
}
