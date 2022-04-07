package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.facade.UserFacade;
import com.example.demo.service.UserService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public UserController(UserService userService, UserFacade userFacade, ResponseErrorValidation responseErrorValidation) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    // возвращаем авторизированного пользователя из объекта principal
    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) throws IOException {
        User userPage = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDTO(userPage, userPage);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId, Principal principal) throws IOException {
        User userPage = userService.getUserById(Long.parseLong(userId));
        User loginUser = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDTO(userPage, loginUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public  ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) throws IOException {
        // check errors
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDTO, principal);

        UserDTO userUpdated = userFacade.userToUserDTO(user, user);
        return new ResponseEntity<>(userUpdated,HttpStatus.OK);
    }

}
