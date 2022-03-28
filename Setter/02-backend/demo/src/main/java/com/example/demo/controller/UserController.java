package com.example.demo.controller;

import com.example.demo.dto.UserAuthorDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.facade.UserAuthorFacade;
import com.example.demo.facade.UserFacade;
import com.example.demo.service.UserService;

import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;
    private final UserAuthorFacade userAuthorFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public UserController(UserService userService,
                          UserFacade userFacade,
                          UserAuthorFacade userAuthorFacade,
                          ResponseErrorValidation responseErrorValidation
    ) {
        this.userService = userService;
        this.userFacade = userFacade;
        this.userAuthorFacade = userAuthorFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    // возвращаем авторизированного пользователя из объекта principal
   @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDTO(user, user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserAuthorDTO>> getAllUsers(@RequestParam(required = false) String lastNameSearch,
                                                           @RequestParam(required = false) String page,
                                                           @RequestParam(required = false) String size
    ) throws IOException {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        List<UserAuthorDTO> userAuthorDTOList;
        if(lastNameSearch == null) {
            userAuthorDTOList = userService.getAllUsers(pageable)
                    .stream()
                    .map(userAuthorFacade::userToUserAuthorDTO)
                    .collect(Collectors.toList());
        } else {
            userAuthorDTOList = userService.searchUsers(lastNameSearch, pageable)
                    .stream()
                    .map(userAuthorFacade::userToUserAuthorDTO)
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllUsers(@RequestParam(required = false) String lastNameSearch) throws IOException {
        Long postsAmount;
        if(lastNameSearch == null) {
            postsAmount = userService.countAllUsers();
        } else {
            postsAmount = userService.countSearchUsers(lastNameSearch);
        }
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserProfile(@PathVariable("userId") String userId, Principal principal) {

        try {
            User viewUser = userService.getCurrentUser(principal);
            User user = userService.getUserById(Long.parseLong(userId));
            UserDTO userDTO = userFacade.userToUserDTO(user, viewUser);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            String errorIdMessage = "User with id: " + userId + " not exists";
            errorMap.put("userIdError", errorIdMessage);
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public  ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        User user = userService.updateUser(userDTO, principal);

        UserDTO userUpdated = userFacade.userToUserDTO(user, user);
        return new ResponseEntity<>(userUpdated,HttpStatus.OK);
    }
}
