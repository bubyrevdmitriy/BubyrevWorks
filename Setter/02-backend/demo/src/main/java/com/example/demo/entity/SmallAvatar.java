package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseFile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "small_avatar")
@Data
public class SmallAvatar extends BaseFile {

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
