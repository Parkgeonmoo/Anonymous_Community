package com.example.anonymous_community.domain.article.entity;

import com.example.anonymous_community.domain.article.dto.request.ArticleEntryRequest;
import com.example.anonymous_community.domain.article.dto.request.ArticleUpdateRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentGetListResponse;
import com.example.anonymous_community.domain.comment.entity.CommentEntity;
import com.example.anonymous_community.global.common.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 Entity
 *
 * @author parkgeonwoo
 */

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "article_tb")
public class ArticleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="article_id", nullable = false)
    private Integer articleIndex;

    @Column
    private String title;

    @Column
    private String nickname;

    @Column
    private String contents;

    @Column
    private String password;

    @OneToMany(mappedBy = "articleIndex", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments = new ArrayList<>();



    private void setTitle(String title) {
        if (!StringUtils.isBlank(title)) {
            this.title = title;
        }
    }

    private void setNickName(String nickName) {
        if (!StringUtils.isBlank(nickName)) {
            this.nickname = nickName;
        }
    }

    private void setContents(String contents) {
        if (!StringUtils.isBlank(contents)) {
            this.contents = contents;
        }
    }


    public void setAllColumns(ArticleUpdateRequest inputArticleUpdateRequest) {
        setTitle(inputArticleUpdateRequest.getTitle());
        setNickName(inputArticleUpdateRequest.getNickName());
        setContents(inputArticleUpdateRequest.getContents());

    }






}
