package com.example.anonymous_community.entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_time",updatable = false)
    private Date createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_time")
    private Date updatedTime;
}
