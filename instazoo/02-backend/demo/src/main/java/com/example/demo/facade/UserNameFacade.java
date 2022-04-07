package com.example.demo.facade;

import com.example.demo.dto.UserNameDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserNameFacade {

    public UserNameDTO userToUserNameDTO(User user) {
        UserNameDTO userNameDTO = new UserNameDTO();
        userNameDTO.setId(user.getId());
        userNameDTO.setFirstname(user.getName());
        userNameDTO.setLastname(user.getLastname());
        userNameDTO.setUsername(user.getUsername());
        return userNameDTO;
    }

}
