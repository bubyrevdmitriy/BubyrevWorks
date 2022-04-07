package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {
    private Long id;
    private String name;
    private byte[] imageBytes;
    protected LocalDateTime createdDate;
}
