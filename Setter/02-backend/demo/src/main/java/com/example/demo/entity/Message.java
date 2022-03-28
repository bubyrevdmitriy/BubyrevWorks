package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import com.example.demo.entity.enums.MessageStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private User recipient;

    @Column(name = "content")
    private String content;

    private MessageStatus status;
}
