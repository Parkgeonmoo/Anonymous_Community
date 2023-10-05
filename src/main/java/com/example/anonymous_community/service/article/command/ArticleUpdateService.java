package com.example.anonymous_community.service.article.command;

import com.example.anonymous_community.dao.ArticleDao;
import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시글 수정 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleUpdateService {

    private final ArticleDao articledao;

    /**
     * 게시글 수정
     *
     * @param inputArticleRequest {@link ArticleRequest}
     */
    @Transactional
    public void putArticleService(ArticleRequest inputArticleRequest) {

        final String articleIndex = inputArticleRequest.getArticleIndex();
        if (!NumberUtils.isDigits(articleIndex)){
            log.error("해당 글을 조회하실 수 없습니다.");
            return;
        }

        if (NumberUtils.toInt(articleIndex) <= 0) {
            log.error("해당 글을 조회하실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputArticleRequest.getPassword())) {
            log.error("비밀번호가 비어있습니다.");
            return;
        }

        if (StringUtils.isAllBlank(inputArticleRequest.getTitle(), inputArticleRequest.getNickName(), inputArticleRequest.getContents())) {
            log.error("제목, 닉네임, 내용 중 적어도 하나는 입력되어야 합니다.");
            return;
        }

        final ArticleEntity returnArticleEntity = new ArticleEntity(inputArticleRequest);
        if (articledao.update(returnArticleEntity) == null) {
            log.error("수정하고자 하는 글이 존재하지 않습니다.");
        }
    }
}
