package com.example.anonymous_community.repository;

import com.example.anonymous_community.entity.articleEntity;
import com.example.anonymous_community.entity.commentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<commentEntity,String> {
}
