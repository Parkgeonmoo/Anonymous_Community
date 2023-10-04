package com.example.anonymous_community.repository;

import com.example.anonymous_community.dto.ArticleRequest;
import com.example.anonymous_community.entity.ArticleEntity;
import com.example.anonymous_community.entity.QArticleEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public ArticleRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(ArticleEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;  // 주입받은 JPAQueryFactory 인스턴스 저장
    }

    public ArticleEntity findByIndex(String index) {
        QArticleEntity qArticleEntity = QArticleEntity.articleEntity;

        return jpaQueryFactory
                .selectFrom(qArticleEntity)
                .where(qArticleEntity.articleIndex.eq(index))
                .fetchOne();
    }

    public List<ArticleEntity> findFirst(int page, int limit) {
        QArticleEntity qArticleEntity = QArticleEntity.articleEntity;

        return jpaQueryFactory
                .selectFrom(qArticleEntity)
                .orderBy(qArticleEntity.articleIndex.asc())  // 선택적: 내림차순 정렬
                .offset((page - 1) * limit)  // 페이지 번호는 1부터 시작한다고 가정
                .limit(limit)
                .fetch();
    }


}
