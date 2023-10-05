package com.example.anonymous_community.service.comment.query;

import com.example.anonymous_community.dto.CommentRequest;
import com.example.anonymous_community.entity.CommentEntity;
import com.example.anonymous_community.repository.CommentRepositoySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 댓글 목록 조회 service
 *
 * @author parkgeonwoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentListService {
    private final CommentRepositoySupport commentRepositoySupport;

    /**
     * 댓글 목록 조회
     * @param articleIndex 게시글 고유번호
     * @return {@link List}
     */
    @Transactional(readOnly = true)
    public List<CommentRequest> getList(String articleIndex) {

        if (!NumberUtils.isDigits(articleIndex)) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }
        final int index = NumberUtils.toInt(articleIndex);
        if (index <= 0) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }

        final List<CommentRequest> returnCommentRequests = new ArrayList<>();
        final List<CommentEntity> commentEntities = commentRepositoySupport.findByIndex(articleIndex);

        if (commentEntities == null) {
            log.info("조회하신 댓글이 존재하지 않습니다.");
            return null;
        }

        for (CommentEntity temp : commentEntities) {
            returnCommentRequests.add(new CommentRequest(temp));
        }
        return returnCommentRequests;
    }
}
