package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.request.ArticleRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 게시글 Entity
 *
 * @author parkgeonwoo
 */
@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article_tb")
public class ArticleEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="article_id", nullable = false)
    private String articleIndex;

    @Column
    private String title;

    @Column
    private String nickname;

    @Column
    private String contents;

    @Column
    private String password;


    public ArticleEntity(ArticleRequest inputArticleRequest) {
        this.articleIndex = inputArticleRequest.getArticleIndex();
        this.title = inputArticleRequest.getTitle();
        this.nickname = inputArticleRequest.getNickName();
        this.contents = inputArticleRequest.getContents();
        this.password = inputArticleRequest.getPassword();

    }



}
