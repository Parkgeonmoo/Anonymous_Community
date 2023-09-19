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
        Comment returnComment = new Comment();
        CommentEntity CommentEntity = new CommentEntity();

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


        CommentEntity.setCommentIndex(inputComment.getCommentIndex());
        CommentEntity.setArticleIndex(inputComment.getArticleIndex());
        CommentEntity.setNickName(inputComment.getNickName());
        CommentEntity.setContents(inputComment.getContents());
        CommentEntity.setPassword(inputComment.getPassword());
        CommentEntity.setCreated_Time(inputComment.getCreatedTime());
        CommentEntity.setUpdated_Time(inputComment.getUpdatedTime());

        CommentEntity = commentdao.postCommentEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("작성하신 댓글을 저장하지 못하였습니다.");
            return null;
        }



        returnComment.setCommentIndex(CommentEntity.getCommentIndex());
        returnComment.setArticleIndex(CommentEntity.getArticleIndex());
        returnComment.setNickName(CommentEntity.getNickName());
        returnComment.setContents(CommentEntity.getContents());
        returnComment.setPassword(CommentEntity.getPassword());
        returnComment.setCreatedTime(CommentEntity.getCreated_Time());
        returnComment.setUpdatedTime(CommentEntity.getUpdated_Time());


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
            Comment tempComment = new Comment();
            tempComment.setCommentIndex(temp.getCommentIndex());
            tempComment.setArticleIndex(temp.getArticleIndex());
            tempComment.setNickName(temp.getNickName());
            tempComment.setContents(temp.getContents());
            tempComment.setPassword(temp.getPassword());
            tempComment.setCreatedTime(temp.getCreated_Time());
            tempComment.setUpdatedTime(temp.getUpdated_Time());
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



        CommentEntity CommentEntity = new CommentEntity();
        Comment returnComment = new Comment();

        LocalDateTime now = LocalDateTime.now();
        inputComment.setUpdatedTime(now.toString());
        System.out.println(now.toString());

        CommentEntity.setArticleIndex(inputComment.getArticleIndex());
        CommentEntity.setCommentIndex(inputComment.getCommentIndex());
        CommentEntity.setContents(inputComment.getContents());
        CommentEntity.setPassword(inputComment.getPassword());
        CommentEntity.setUpdated_Time(inputComment.getUpdatedTime());

        CommentEntity = commentdao.putCommentEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("수정하신 댓글을 저장하지 못하였습니다.");
        }


        returnComment.setArticleIndex(CommentEntity.getArticleIndex());
        returnComment.setCommentIndex(CommentEntity.getCommentIndex());
        returnComment.setNickName(CommentEntity.getNickName());
        returnComment.setContents(CommentEntity.getContents());
        returnComment.setUpdatedTime(CommentEntity.getUpdated_Time());


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

        CommentEntity CommentEntity = new CommentEntity();
        Comment returnComment = new Comment();




        CommentEntity.setArticleIndex(inputComment.getArticleIndex());
        CommentEntity.setCommentIndex(inputComment.getCommentIndex());
        CommentEntity.setPassword(inputComment.getPassword());

        CommentEntity = commentdao.deleteEntity(CommentEntity);

        if (CommentEntity == null) {
            log.error("지우고자 하는 댓글을 지우지 못하였습니다.");
        }

        returnComment.setArticleIndex(CommentEntity.getArticleIndex());
        returnComment.setCommentIndex(CommentEntity.getCommentIndex());
        returnComment.setNickName(CommentEntity.getNickName());
        returnComment.setContents(CommentEntity.getContents());
        returnComment.setCreatedTime(CommentEntity.getCreated_Time());
        returnComment.setUpdatedTime(CommentEntity.getUpdated_Time());


        return returnComment;
    }
}
