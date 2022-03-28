package com.example.demo.dto;

import lombok.Data;

@Data
public class UserGroupInviteDTO {

    private Long id;
    private UserGroupAuthorDTO futureGroup;
    private UserAuthorDTO otherUser;
}
