package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group")
@Data
public class UserGroup extends BaseEntity {

    @Column(name="name", nullable = false)
    private String name;

    @Column(columnDefinition = "text", name="description")
    private String description;

    private Long profileImageId;

    private Integer userMembershipAmount;

    @OneToOne(mappedBy = "userGroup", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SmallAvatarGroup smallAvatarGroup;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<CommonImage> commonImages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<AudioFile> audioFiles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<VideoFile> videoFiles = new ArrayList<>();




    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<UserGroupInvite> userInvites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "userGroup", orphanRemoval = true)
    private List<UserGroupPair> userPairs = new ArrayList<>();
}
