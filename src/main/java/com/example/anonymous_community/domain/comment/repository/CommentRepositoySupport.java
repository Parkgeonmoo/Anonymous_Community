package com.example.anonymous_community.domain.comment.repository;
import com.example.anonymous_community.domain.comment.entity.QCommentEntity;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
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
    /**
    public List<CommentEntity> findByIndex(Integer index) {
        QCommentEntity qCommentEntity = QCommentEntity.commentEntity;
        return jpaQueryFactory
                .selectFrom(qCommentEntity)
                .where(qCommentEntity.articleIndex.eq(index))
                .fetch();
    }
     **/
}
