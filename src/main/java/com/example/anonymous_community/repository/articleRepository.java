package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.articleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface articleRepository extends JpaRepository<articleEntity,String> {
}
