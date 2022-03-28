package com.example.demo.entity;

import com.example.demo.entity.BaseEntity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channel")
@Data
public class Channel  extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User senderUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User recipientUser;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "channel", orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();
}
