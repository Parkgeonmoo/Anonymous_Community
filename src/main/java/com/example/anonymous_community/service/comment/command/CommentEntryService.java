package com.example.anonymous_community.service.comment.command;

import com.example.anonymous_community.dto.request.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.CommentDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 등록 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentEntryService {
    private final CommentDao commentdao;

    /**
     * 댓글 등록
     *
     * @param inputCommentRequest {@link CommentRequest}
     */
    @Transactional
    public void entry(CommentRequest inputCommentRequest) {

        final String articleIndex = inputCommentRequest.getArticleIndex();
        if (!NumberUtils.isDigits(articleIndex)){
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return;
        }

        if (NumberUtils.toInt(articleIndex) <= 0) {
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getNickName())) {
            log.error("닉네임이 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getContents())) {
            log.error("댓글 내용이 비어있습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getPassword())) {
            log.error("댓글 비밀번호가 비어있습니다.");
            return;
        }

        final CommentEntity CommentEntity = new CommentEntity(inputCommentRequest);
        if (commentdao.entry(CommentEntity) == null) {
            log.error("작성하신 댓글을 저장하지 못하였습니다.");
        }
    }
}
