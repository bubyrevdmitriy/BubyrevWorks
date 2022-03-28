package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "audio_file")
@Data
public class AudioFile extends BaseEntity {

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
    private Long audioLength;

    @Column(name="likes")
    private Integer likes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "audioFile", orphanRemoval = true)
    private List<UserLike> userLikes = new ArrayList<>();
}
