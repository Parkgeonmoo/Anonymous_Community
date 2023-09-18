package com.example.anonymous_community.service;


import com.example.anonymous_community.dto.comment;
import com.example.anonymous_community.entity.commentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.commentdao;

import java.util.ArrayList;
import java.util.List;

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

            commentEntity.setCommentIndex(inputComment.getCommentIndex());
            commentEntity.setArticleIndex(inputComment.getArticleIndex());
            commentEntity.setNickName(inputComment.getNickName());
            commentEntity.setContents(inputComment.getContents());
            commentEntity.setPassword(inputComment.getPassword());

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

    public List<comment> getCommentService(String articleIndex) {
        List<comment> returnComments = new ArrayList<>();
        List<commentEntity> commentEntity = new ArrayList<>();

        try {
            commentEntity = commentdao.getCommentEntities(articleIndex);

            for (commentEntity temp : commentEntity) {
                comment tempComment = new comment();
                tempComment.setCommentIndex(temp.getCommentIndex());
                tempComment.setArticleIndex(temp.getArticleIndex());
                tempComment.setNickName(temp.getNickName());
                tempComment.setContents(temp.getContents());
                tempComment.setPassword(temp.getPassword());
                tempComment.setCreated_Time(temp.getCreated_Time());
                tempComment.setUpdated_Time(temp.getUpdated_Time());
                returnComments.add(tempComment);
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("조회하신 댓글이 존재하지 않습니다.");
        }
        return returnComments;

    }

    public comment putCommentService(comment inputComment) {
        commentEntity commentEntity = new commentEntity();
        comment returnComment = new comment();
        commentEntity.setArticleIndex(inputComment.getArticleIndex());
        commentEntity.setCommentIndex(inputComment.getCommentIndex());
        commentEntity.setContents(inputComment.getContents());
        commentEntity.setPassword(inputComment.getPassword());

        commentEntity = commentdao.putCommentEntity(commentEntity);

        if (commentEntity != null) {
            returnComment.setArticleIndex(commentEntity.getArticleIndex());
            returnComment.setCommentIndex(commentEntity.getCommentIndex());
            returnComment.setNickName(commentEntity.getNickName());
            returnComment.setContents(commentEntity.getContents());
            returnComment.setUpdated_Time(commentEntity.getUpdated_Time());
        }else{
            System.out.println("수정하고자 하는 댓글이 존재하지 않습니다.");
        }
         return returnComment;
    }

    public comment deleteCommentService(comment inputComment) {
        commentEntity commentEntity = new commentEntity();
        comment returnComment = new comment();
        commentEntity.setArticleIndex(inputComment.getArticleIndex());
        commentEntity.setCommentIndex(inputComment.getCommentIndex());
        commentEntity.setPassword(inputComment.getPassword());

        commentEntity = commentdao.deleteEntity(commentEntity);

        if (commentEntity != null) {
            returnComment.setArticleIndex(commentEntity.getArticleIndex());
            returnComment.setCommentIndex(commentEntity.getCommentIndex());
            returnComment.setNickName(commentEntity.getNickName());
            returnComment.setContents(commentEntity.getContents());
            returnComment.setUpdated_Time(commentEntity.getUpdated_Time());
        }else{
            System.out.println("삭제하고자 하는 댓글이 존재하지 않습니다.");
        }
        return returnComment;
    }
}
