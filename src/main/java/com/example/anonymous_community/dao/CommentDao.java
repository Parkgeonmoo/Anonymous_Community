package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.commentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CommentDao {

    CommentRepository commentRepository;

    public CommentDao(CommentRepository commentRepository) {
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

    public List<commentEntity> getCommentEntities(String articleIndex) {
        List<commentEntity> commentEntities = commentRepository.findByArticleIndex(articleIndex);

        if (commentEntities.isEmpty()) {
            System.out.println("해당 인덱스의 댓글이 없습니다.");
            return null;
        } else {
            return commentEntities;
        }
    }

    public commentEntity putCommentEntity(commentEntity commentEntity) {
        if (commentEntity != null) {
            String articleIndex = commentEntity.getArticleIndex();
            String commentIndex = commentEntity.getCommentIndex();

            // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
            Optional<commentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

            if (existingCommentOptional.isPresent()) {
                commentEntity existingComment = existingCommentOptional.get();

                // 비밀번호 일치 여부 확인
                String savedPassword = existingComment.getPassword();
                String providedPassword = commentEntity.getPassword();

                if (savedPassword.equals(providedPassword)) {
                    // 업데이트할 내용 설정
                    String updatedContent = commentEntity.getContents();
                    existingComment.setContents(updatedContent);

                    // 업데이트된 댓글 저장
                    commentEntity updatedComment = commentRepository.save(existingComment);

                    return updatedComment;
                } else {
                    throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
                }
            }else{
                System.out.println("해당하는 댓글이 존재하지 않습니다.");
            }
        }

        return null;
    }

    public commentEntity deleteEntity(commentEntity commentEntity) {
        if (commentEntity != null) {
            String articleIndex = commentEntity.getArticleIndex();
            String commentIndex = commentEntity.getCommentIndex();

            // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
            Optional<commentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

            if (existingCommentOptional.isPresent()) {
                commentEntity existingComment = existingCommentOptional.get();

                // 비밀번호 일치 여부 확인
                String savedPassword = existingComment.getPassword();
                String providedPassword = commentEntity.getPassword();

                if (savedPassword.equals(providedPassword)) {

                    commentRepository.delete(existingComment);

                    return existingComment;
                } else {
                    throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
                }
            }else{
                System.out.println("해당하는 댓글이 존재하지 않습니다.");
            }
        }
        return null;
    }





}

