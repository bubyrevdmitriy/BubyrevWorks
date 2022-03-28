package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import com.example.demo.entity.BaseEntity.BaseFile;
import lombok.Data;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video_file")
@Data
public class VideoFile extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;

    @Column
    private String fileName;

    @Column
    private String contentType;

    @Column
    private String description;

    @Column
    private Long fileSize;

    @Column
    private Long videoLength;

    @Column(name="likes")
    private Integer likes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "videoFile", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
            mappedBy = "videoFile", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
