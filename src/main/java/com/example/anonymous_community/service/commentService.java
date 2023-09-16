package com.example.anonymous_community.service;


import com.example.anonymous_community.dto.comment;
import com.example.anonymous_community.entity.commentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.anonymous_community.dao.commentdao;

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
        //테스트 커밋용
    }
}
