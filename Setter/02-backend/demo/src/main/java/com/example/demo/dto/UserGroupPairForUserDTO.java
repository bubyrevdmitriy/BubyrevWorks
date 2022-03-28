package com.example.demo.dto;

import lombok.Data;

@Data
public class UserGroupPairForUserDTO {

    private Long id;
    private Long userId;
    private UserGroupAuthorDTO myGroup;
    private Boolean isUserAdmin;
}
