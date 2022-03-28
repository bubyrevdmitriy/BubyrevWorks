package com.example.demo.dto;

import lombok.Data;

@Data
public class UserGroupPairForUserGroupDTO {

    private Long id;
    private Long userGroupId;
    private UserAuthorDTO user;
    private Boolean isUserAdmin;
}
