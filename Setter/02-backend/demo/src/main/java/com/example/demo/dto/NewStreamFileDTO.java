package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewStreamFileDTO {
    private String description;
    private String groupId;
    //private MultipartFile file;
}
