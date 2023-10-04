package com.example.anonymous_community.service.article.command;

import com.example.anonymous_community.dao.ArticleDao;
import com.example.anonymous_community.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 /**
 * 게시글 삭제 service
 *
 * @author parkgeonwoo
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleDeleteService {

    private final ArticleDao articledao;

     /**
      * 게시글 삭제
      *
      * @param articleIndex 게시글 고유번호
      * @param password 게시글 비밀번호
      */
    @Transactional
    public void delete(String articleIndex, String password) {

        if (!NumberUtils.isDigits(articleIndex)){
            log.error("해당 글을 지울 수 없습니다.");
            return;
        }

        if (NumberUtils.toInt(articleIndex) <= 0) {
            log.error("해당 글을 지울 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(password) || !password.matches("^[a-zA-Z0-9]{6,10}$")) {
            log.error("비밀번호가 유효하지 않습니다. 6-10자리의 영어 대소문자와 숫자만 가능합니다.");
            return;
        }

        final ArticleEntity returnArticleEntity = articledao.delete(articleIndex,password);
        if (returnArticleEntity == null) {
            log.error("지우고자 하는 글이 존재하지 않습니다.");
        }
    }
}
