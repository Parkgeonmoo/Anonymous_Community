package com.example.anonymous_community.repository;
import com.example.anonymous_community.entity.QCommentEntity;

import com.example.anonymous_community.entity.CommentEntity;
import com.example.anonymous_community.entity.QArticleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 댓글 support
 *
 * @author parkgeonwoo
 */
@Repository
public class CommentRepositoySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public CommentRepositoySupport(EntityManager entityManager) {
        super(CommentEntity.class);
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public List<CommentEntity> findByIndex(String index) {
        QCommentEntity qCommentEntity = QCommentEntity.commentEntity;
        return jpaQueryFactory
                .selectFrom(qCommentEntity)
                .where(qCommentEntity.articleIndex.eq(index))
                .fetch();
    }
}
