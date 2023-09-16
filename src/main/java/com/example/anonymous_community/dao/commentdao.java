package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.commentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.repository.commentRepository;

import java.util.Optional;

@Repository
@Slf4j
public class commentdao {

    commentRepository commentRepository;

    public commentdao(commentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public commentEntity postCommentEntity(commentEntity commentEntity) {

        commentEntity returnCommentEntity = null;
        if (commentEntity == null) {
            System.out.println("댓글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }

        try {

            returnCommentEntity = commentRepository.save(commentEntity);
        }catch(Exception e) {
            System.out.println("댓글쓰기 저장 실패입니다.");
            e.printStackTrace();  // 여기에 추가
            return null;
        }

        return returnCommentEntity;
    }

    public commentEntity getCommentEntity(String commentIndex) {
        commentEntity returnCommentEntity = null;
        Optional<commentEntity> optionalCommentEntity = commentRepository.findById(commentIndex);

        if (optionalCommentEntity.isPresent()) {
            returnCommentEntity = optionalCommentEntity.get();
            return returnCommentEntity;
        } else {
            System.out.println("해당 인덱스의 댓글이 없습니다.");
            return null;
        }
    }

}

