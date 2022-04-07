package com.example.demo.entity;

import com.example.demo.entity.baseEntity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class Post extends BaseEntity {

    private String title;
    private String caption;
    private String location;
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, /*fetch = FetchType.EAGER,*/
            mappedBy = "post", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ImageModel> imageModels = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "post", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();
}
