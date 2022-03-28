package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UserDTO {

    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String middleName;

    private String bio;
    private String phone;
    private String email;

    private ImageDTO profilePhoto;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;

    private Boolean isLoginUserPage;
    private Boolean isLoginUserFriend;
    private Boolean sendToLoginUserFriendInvite;
    private Boolean receiveFromLoginUserFriendInvite;

    private Long channelIdWithLoginUserPage;

}
