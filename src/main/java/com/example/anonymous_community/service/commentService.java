package com.example.anonymous_community.service;


import com.example.anonymous_community.dto.comment;
import com.example.anonymous_community.entity.commentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.commentdao;

import java.time.LocalDateTime;

@Service
@Slf4j
public class commentService {
    commentdao commentdao = null;

    public commentService(commentdao commentdao) {
        this.commentdao = commentdao;
    }

    public comment postCommentService(comment inputComment) {
        comment returnComment = new comment();
        commentEntity commentEntity = new commentEntity();

        if (inputComment == null) {
            System.out.println("댓글 내용이 비어있습니다.");
            return null;
        }

        try{

                LocalDateTime now = LocalDateTime.now();

                inputComment.setCreated_Time(now.toString());
                inputComment.setUpdated_Time(now.toString());

                commentEntity.setCommentIndex(inputComment.getCommentIndex());
                commentEntity.setArticleIndex(inputComment.getArticleIndex());
                commentEntity.setNickName(inputComment.getNickName());
                commentEntity.setContents(inputComment.getContents());
                commentEntity.setPassword(inputComment.getPassword());
                commentEntity.setCreated_Time(inputComment.getCreated_Time());
                commentEntity.setUpdated_Time(inputComment.getUpdated_Time());

                System.out.println(commentEntity.toString());
                commentEntity = commentdao.postCommentEntity(commentEntity);

                returnComment.setCommentIndex(commentEntity.getCommentIndex());
                returnComment.setArticleIndex(commentEntity.getArticleIndex());
                returnComment.setNickName(commentEntity.getNickName());
                returnComment.setContents(commentEntity.getContents());
                returnComment.setPassword(commentEntity.getPassword());
                returnComment.setCreated_Time(commentEntity.getCreated_Time());
                returnComment.setUpdated_Time(commentEntity.getUpdated_Time());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("댓글 내용이 비어있습니다.");
        }


        return returnComment;

    }

    public comment getCommentService(String commentIndex) {
        comment returnComment = new comment();
        commentEntity commentEntity = new commentEntity();

        try {
            commentEntity = commentdao.getCommentEntity(commentIndex);

            returnComment.setCommentIndex(commentEntity.getCommentIndex());
            returnComment.setArticleIndex(commentEntity.getArticleIndex());
            returnComment.setNickName(commentEntity.getNickName());
            returnComment.setContents(commentEntity.getContents());
            returnComment.setPassword(commentEntity.getPassword());
            returnComment.setCreated_Time(commentEntity.getCreated_Time());
            returnComment.setUpdated_Time(commentEntity.getUpdated_Time());

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("조회하신 댓글이 존재하지 않습니다.");
        }
       return returnComment;
    }
}
