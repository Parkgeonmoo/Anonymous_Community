package com.example.anonymous_community.service.comment.command;

import com.example.anonymous_community.dao.CommentDao;
import com.example.anonymous_community.dto.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentDeleteService {
    private final CommentDao commentdao;

    @Transactional
    public void deleteCommentService(CommentRequest inputCommentRequest) {

        int articleIndex = 0;
        int commentIndex = 0;

        try{
            articleIndex = Integer.parseInt(inputCommentRequest.getArticleIndex());
            commentIndex = Integer.parseInt(inputCommentRequest.getCommentIndex());
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 지우실 수 없습니다.");
            return;
        }

        if (StringUtils.isBlank(inputCommentRequest.getPassword())) {
            log.error("댓글 삭제를 위해 댓글 비밀번호를 입력해주세요.");
            return;
        }

        CommentEntity CommentEntity = new CommentEntity(inputCommentRequest);


        CommentEntity = commentdao.deleteEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("지우고자 하는 댓글을 지우지 못하였습니다.");
        }

    }
}
