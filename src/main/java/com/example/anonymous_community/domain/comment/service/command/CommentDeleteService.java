package com.example.anonymous_community.domain.comment.service.command;

import com.example.anonymous_community.domain.comment.dao.CommentDao;
import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 삭제 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentDeleteService {
    private final CommentDao commentdao;

    /**
     * 댓글 삭제
     *
     * @param inputCommentEntryRequest {@link CommentEntryRequest}
     */

    /**
    @Transactional
    public void delete(CommentEntryRequest inputCommentEntryRequest) {

        final String articleIndex = inputCommentEntryRequest.getArticleIndex();
        final String commentIndex = inputCommentEntryRequest.getCommentIndex();
        if (!NumberUtils.isDigits(articleIndex) || !NumberUtils.isDigits(commentIndex)) {
            log.error("해당 댓글을 지우실 수 없습니다.");
            return;
        } else if (NumberUtils.toInt(articleIndex) <= 0 || NumberUtils.toInt(commentIndex) <= 0) {
            log.error("해당 댓글을 지우실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentEntryRequest.getPassword())) {
            log.error("댓글 삭제를 위해 댓글 비밀번호를 입력해주세요.");
            return;
        }

        final CommentEntity CommentEntity = new CommentEntity(inputCommentEntryRequest);
        if (commentdao.delete(CommentEntity) == null) {
            log.error("지우고자 하는 댓글을 지우지 못하였습니다.");
        }

    }
    **/
}
