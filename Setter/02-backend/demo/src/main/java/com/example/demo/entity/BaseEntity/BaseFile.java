package com.example.demo.entity.BaseEntity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseFile extends BaseEntity {

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String address;
}
