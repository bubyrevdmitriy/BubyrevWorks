package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseFile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "small_avatar_group")
@Data
public class SmallAvatarGroup extends BaseFile {

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;
}
