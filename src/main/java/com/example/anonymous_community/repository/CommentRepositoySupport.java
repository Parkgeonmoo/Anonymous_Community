package com.example.anonymous_community.repository;
import com.example.anonymous_community.entity.QCommentEntity;

import com.example.anonymous_community.entity.CommentEntity;
import com.example.anonymous_community.entity.QArticleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public CommentRepositoySupport(JPAQueryFactory jpaQueryFactory) {
        super(CommentEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;  // 주입받은 JPAQueryFactory 인스턴스 저장
    }

    public List<CommentEntity> findByIndex(String index) {
        QCommentEntity qCommentEntity = QCommentEntity.commentEntity;

        return jpaQueryFactory
                .selectFrom(qCommentEntity)
                .where(qCommentEntity.articleIndex.eq(index))
                .fetch();
    }
}
