package com.example.anonymous_community.domain.article.repository;

import com.example.anonymous_community.domain.article.entity.ArticleEntity;
import com.example.anonymous_community.domain.article.entity.QArticleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public Optional<ArticleEntity> findByIndex(Integer index) {
        final QArticleEntity qArticleEntity = QArticleEntity.articleEntity;
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(qArticleEntity)
                .where(qArticleEntity.articleIndex.eq(index))
                .fetchFirst());
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
