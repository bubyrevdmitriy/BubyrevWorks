package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_group_invite")
@Data
public class UserGroupInvite  extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User senderUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User recipientUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;
}
