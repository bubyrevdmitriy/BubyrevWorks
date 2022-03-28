package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseFile;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "common_image")
@Data
public class CommonImage extends BaseFile {

    @Column(name="likes")
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "commonImage", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "commonImage", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();

}
