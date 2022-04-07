package com.example.demo.entity.baseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass//делаем класс абстрактным
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name="created_Date", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name="updated_Date",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedDate;

    @PrePersist//запоняет время во время сощдания сущности
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
