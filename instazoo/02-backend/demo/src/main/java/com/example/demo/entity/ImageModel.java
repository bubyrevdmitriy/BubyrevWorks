package com.example.demo.entity;

import com.example.demo.entity.baseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
@Data
public class ImageModel extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
