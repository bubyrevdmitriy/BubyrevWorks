package com.example.demo.entity;

import com.example.demo.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "text", nullable = false)
    private String message;
}
