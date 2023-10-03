package com.example.anonymous_community.service.comment.command;


import com.example.anonymous_community.dto.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.CommentDao;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentEntryService {
    private final CommentDao commentdao;

    @Transactional
    public void postCommentService(CommentRequest inputCommentRequest) {

        int index = 0;

        try{
            index = Integer.parseInt(inputCommentRequest.getArticleIndex());
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return;
        }

        if (index <= 0) {
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return;
        }

        if (inputCommentRequest.getNickName() == null) {
            log.error("닉네임이 비어있습니다.");
            return;
        }

        if (inputCommentRequest.getContents() == null) {
            log.error("댓글 내용이 비어있습니다.");
            return;
        }

        if (inputCommentRequest.getPassword() == null) {
            log.error("댓글 비밀번호가 비어있습니다.");
            return;
        }


        LocalDateTime now = LocalDateTime.now();



        CommentEntity CommentEntity = new CommentEntity(inputCommentRequest);


        CommentEntity = commentdao.postCommentEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("작성하신 댓글을 저장하지 못하였습니다.");
            return;
        }



    }

}
