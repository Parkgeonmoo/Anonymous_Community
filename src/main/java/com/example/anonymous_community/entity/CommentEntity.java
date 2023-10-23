package com.example.anonymous_community.entity;

import com.example.anonymous_community.dto.request.CommentRequest;
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
 * 댓글 Entity
 *
 * @author parkgeonwoo
 */
@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment_tb")
public class CommentEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="comment_id", nullable = false)
    private String commentIndex;

    @Column(name ="article_id",nullable = false)
    private String articleIndex;

    @Column(name ="nickname",nullable = false)
    private String nickName;

    @Column(name ="contents")
    private String contents;

    @Column(name ="password",nullable = false)
    private String password;


    public CommentEntity(CommentRequest commentRequest) {
        this.commentIndex = commentRequest.getCommentIndex();
        this.articleIndex = commentRequest.getArticleIndex();
        this.nickName = commentRequest.getNickName();
        this.contents = commentRequest.getContents();
        this.password = commentRequest.getPassword();

    }

}
