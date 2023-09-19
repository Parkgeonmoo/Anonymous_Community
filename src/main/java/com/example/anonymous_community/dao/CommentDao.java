package com.example.anonymous_community.dao;

import com.example.anonymous_community.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.example.anonymous_community.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentDao {

    private final CommentRepository commentRepository;

    public CommentEntity postCommentEntity(CommentEntity CommentEntity) {

        CommentEntity returnCommentEntity = null;
        if (CommentEntity == null) {
            System.out.println("댓글쓰기 저장 실패입니다. 전달된 객체가 null입니다.");
            return null;
        }

        try {

            returnCommentEntity = commentRepository.save(CommentEntity);
        } catch(Exception e) {
            System.out.println("댓글쓰기 저장 실패입니다.");
            e.printStackTrace();  // 여기에 추가
            return null;
        }

        return returnCommentEntity;
    }

    public List<CommentEntity> getCommentEntities(String articleIndex) {
        List<CommentEntity> commentEntities = commentRepository.findByArticleIndex(articleIndex);

        if (commentEntities.isEmpty()) {
            System.out.println("해당 인덱스의 댓글이 없습니다.");
            return null;
        } else {
            return commentEntities;
        }
    }

    public CommentEntity putCommentEntity(CommentEntity CommentEntity) {
        if (CommentEntity != null) {
            String articleIndex = CommentEntity.getArticleIndex();
            String commentIndex = CommentEntity.getCommentIndex();

            // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
            Optional<CommentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

            if (existingCommentOptional.isPresent()) {
                CommentEntity existingComment = existingCommentOptional.get();

                // 비밀번호 일치 여부 확인
                String savedPassword = existingComment.getPassword();
                String providedPassword = CommentEntity.getPassword();

                if (savedPassword.equals(providedPassword)) {
                    // 업데이트할 내용 설정
                    String updatedContent = CommentEntity.getContents();
                    String updatedTime = CommentEntity.getUpdatedTime();
                    existingComment.setContents(updatedContent);
                    existingComment.setUpdatedTime(updatedTime);

                    // 업데이트된 댓글 저장
                    CommentEntity updatedComment = commentRepository.save(existingComment);

                    return updatedComment;
                } else {
                    throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
                }
            } else {
                log.error("해당하는 댓글이 존재하지 않습니다.");
            }
        }

        return null;
    }

    public CommentEntity deleteEntity(CommentEntity CommentEntity) {
        if (CommentEntity != null) {
            String articleIndex = CommentEntity.getArticleIndex();
            String commentIndex = CommentEntity.getCommentIndex();

            // JPA를 사용하여 articleIndex와 commentIndex에 해당하는 댓글 조회
            Optional<CommentEntity> existingCommentOptional = Optional.ofNullable(commentRepository.findByArticleIndexAndCommentIndex(articleIndex, commentIndex));

            if (existingCommentOptional.isPresent()) {
                CommentEntity existingComment = existingCommentOptional.get();

                // 비밀번호 일치 여부 확인
                String savedPassword = existingComment.getPassword();
                String providedPassword = CommentEntity.getPassword();

                if (savedPassword.equals(providedPassword)) {

                    commentRepository.delete(existingComment);

                    return existingComment;
                } else {
                    throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
                }
            } else {
                log.error("해당하는 댓글이 존재하지 않습니다.");
            }
        }
        return null;
    }





}

