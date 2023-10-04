package com.example.anonymous_community.service.comment.query;

import com.example.anonymous_community.dao.CommentDao;
import com.example.anonymous_community.dto.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import com.example.anonymous_community.repository.CommentRepositoySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentListService {
    private final CommentDao commentdao;
    private final CommentRepositoySupport commentRepositoySupport;

    @Transactional(readOnly = true)
    public List<CommentRequest> getCommentService(String articleIndex) {

        int index = 0;

        try{
            index = Integer.parseInt(articleIndex);
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }


        List<CommentRequest> returnCommentRequests = new ArrayList<>();
        List<CommentEntity> commentEntities = commentRepositoySupport.findByIndex(articleIndex);

        if (commentEntities == null) {
            log.error("조회하신 댓글이 존재하지 않습니다.");
        }


        for (CommentEntity temp : commentEntities) {
            CommentRequest tempCommentRequest = new CommentRequest(temp);
            returnCommentRequests.add(tempCommentRequest);
        }

        return returnCommentRequests;

    }
}
