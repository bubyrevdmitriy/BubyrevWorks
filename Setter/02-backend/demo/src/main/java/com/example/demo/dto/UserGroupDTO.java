package com.example.demo.dto;

import lombok.Data;

@Data
public class UserGroupDTO {

    private Long id;
    private String name;
    private String description;
    private ImageDTO profilePhoto;

    private Boolean isLoginUserAdmin;
    private Boolean isLoginUserMember;
    private Boolean isLoginUserHasInvite;
}
