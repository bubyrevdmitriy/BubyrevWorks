package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment")
@Data
public class Comment extends BaseEntity {

    @Column(columnDefinition = "text", nullable = false)
    private String text;

    @Column(name="likes")
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommonImage commonImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private VideoFile videoFile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "comment", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();
}
