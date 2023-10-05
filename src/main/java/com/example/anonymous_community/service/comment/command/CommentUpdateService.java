package com.example.anonymous_community.service.comment.command;

import com.example.anonymous_community.dao.CommentDao;
import com.example.anonymous_community.dto.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 수정 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentUpdateService {
    private final CommentDao commentdao;

    /**
     * 댓글 수정
     *
     * @param inputCommentRequest {@link CommentRequest}
     */
    @Transactional
    public void putCommentService(CommentRequest inputCommentRequest) {

        final String articleIndex = inputCommentRequest.getArticleIndex();
        final String commentIndex = inputCommentRequest.getCommentIndex();
        if (!NumberUtils.isDigits(articleIndex) || !NumberUtils.isDigits(commentIndex)) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return;
        } else if (NumberUtils.toInt(articleIndex) <= 0 || NumberUtils.toInt(commentIndex) <= 0) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getContents())) {
            log.error("댓글 수정을 위해 댓글 내용을 작성해주세요.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getPassword())) {
            log.error("댓글 수정을 위해 댓글 비밀번호를 입력해주세요.");
            return;
        }

        final CommentEntity CommentEntity = new CommentEntity(inputCommentRequest);
        if (commentdao.update(CommentEntity) == null) {
            log.error("수정하신 댓글을 저장하지 못하였습니다.");
        }
    }
}
