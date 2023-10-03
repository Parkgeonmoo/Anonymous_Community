package com.example.anonymous_community.service;


import com.example.anonymous_community.dto.Comment;
import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.CommentDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentEntryService {
    private final CommentDao commentdao;

    public Comment postCommentService(Comment inputComment) {

        int index = 0;

        try{
            index = Integer.parseInt(inputComment.getArticleIndex());
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return null;
        }

        if (index <= 0) {
            log.error("해당 댓글을 작성하실 수 없습니다.");
            return null;
        }

        if (inputComment.getNickName() == null) {
            log.error("닉네임이 비어있습니다.");
            return null;
        }

        if (inputComment.getContents() == null) {
            log.error("댓글 내용이 비어있습니다.");
            return null;
        }

        if (inputComment.getPassword() == null) {
            log.error("댓글 비밀번호가 비어있습니다.");
            return null;
        }


        LocalDateTime now = LocalDateTime.now();

        inputComment.setCreatedTime(now.toString());
        inputComment.setUpdatedTime(now.toString());


        CommentEntity CommentEntity = new CommentEntity(inputComment);


        CommentEntity = commentdao.postCommentEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("작성하신 댓글을 저장하지 못하였습니다.");
            return null;
        }

        Comment returnComment = new Comment(CommentEntity);

        return returnComment;

    }

    public List<Comment> getCommentService(String articleIndex) {

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


        List<Comment> returnComments = new ArrayList<>();
        List<CommentEntity> CommentEntity = new ArrayList<>();

        CommentEntity = commentdao.getCommentEntities(articleIndex);

        if (CommentEntity == null) {
            log.error("조회하신 댓글이 존재하지 않습니다.");
        }


        for (CommentEntity temp : CommentEntity) {
            Comment tempComment = new Comment(temp);
            returnComments.add(tempComment);
        }

        return returnComments;

    }

    public Comment putCommentService(Comment inputComment) {

        int articleIndex = 0;
        int commentIndex = 0;

        try{
            articleIndex = Integer.parseInt(inputComment.getArticleIndex());
            commentIndex = Integer.parseInt(inputComment.getCommentIndex());
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }

        if (articleIndex <= 0 || commentIndex <= 0) {
            log.error("해당 댓글을 조회하실 수 없습니다.");
            return null;
        }

        if (inputComment.getContents() == null) {
            log.error("댓글 수정을 위해 댓글 내용을 작성해주세요.");
            return null;
        }

        if (inputComment.getPassword() == null) {
            log.error("댓글 수정을 위해 댓글 비밀번호를 입력해주세요.");
            return null;
        }






        LocalDateTime now = LocalDateTime.now();
        inputComment.setUpdatedTime(now.toString());

        CommentEntity CommentEntity = new CommentEntity(inputComment);
        CommentEntity = commentdao.putCommentEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("수정하신 댓글을 저장하지 못하였습니다.");
        }

        Comment returnComment = new Comment(CommentEntity);
         return returnComment;
    }

    public Comment deleteCommentService(Comment inputComment) {

        int articleIndex = 0;
        int commentIndex = 0;

        try{
            articleIndex = Integer.parseInt(inputComment.getArticleIndex());
            commentIndex = Integer.parseInt(inputComment.getCommentIndex());
        } catch(NumberFormatException e) {
            log.error("해당 댓글을 지우실 수 없습니다.");
            return null;
        }

        if (inputComment.getPassword() == null) {
            log.error("댓글 삭제를 위해 댓글 비밀번호를 입력해주세요.");
            return null;
        }

        CommentEntity CommentEntity = new CommentEntity(inputComment);


        CommentEntity = commentdao.deleteEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("지우고자 하는 댓글을 지우지 못하였습니다.");
        }

        Comment returnComment = new Comment(CommentEntity);

        return returnComment;
    }
}
