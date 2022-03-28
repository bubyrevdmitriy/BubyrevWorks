package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_group_pair")
@Data
public class UserGroupPair extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;

    private Boolean isAdmin;
}
