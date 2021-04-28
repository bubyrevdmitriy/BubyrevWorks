package com.bubyrevdmitriyGmail.demo.dto;

import lombok.Data;

@Data
public class FindUserByIdDto {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public FindUserByIdDto() {
    }
}
