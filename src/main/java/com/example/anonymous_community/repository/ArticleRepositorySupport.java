package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.entity.QArticleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 게시글 support
 *
 * @author parkgeonwoo
 */
@Repository
public class ArticleRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public ArticleRepositorySupport(EntityManager entityManager) {
        super(ArticleEntity.class);
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    public ArticleEntity findByIndex(String index) {
        final QArticleEntity qArticleEntity = QArticleEntity.articleEntity;
        return jpaQueryFactory
                .selectFrom(qArticleEntity)
                .where(qArticleEntity.articleIndex.eq(index))
                .fetchOne();
    }

    public List<ArticleEntity> findFirst(int page, int limit) {
        final QArticleEntity qArticleEntity = QArticleEntity.articleEntity;
        return jpaQueryFactory
                .selectFrom(qArticleEntity)
                .orderBy(qArticleEntity.articleIndex.asc())  // 선택적: 내림차순 정렬
                .offset((page - 1) * limit)  // 페이지 번호는 1부터 시작한다고 가정
                .limit(limit)
                .fetch();
    }
}
