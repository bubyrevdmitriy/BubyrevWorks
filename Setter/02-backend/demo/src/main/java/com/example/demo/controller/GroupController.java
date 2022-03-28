package com.example.demo.controller;

import com.example.demo.dto.UserGroupAuthorDTO;
import com.example.demo.dto.UserGroupDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.facade.UserGroupAuthorFacade;
import com.example.demo.facade.UserGroupFacade;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.UserGroupService;
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
@RequestMapping("api/group")
@CrossOrigin
public class GroupController {

    private final UserGroupService userGroupService;
    private final UserService userService;
    private final UserGroupFacade userGroupFacade;
    private final UserGroupAuthorFacade userGroupAuthorFacade;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public GroupController(UserGroupService userGroupService,
                           UserService userService,
                           UserGroupFacade userGroupFacade,
                           UserGroupAuthorFacade userGroupAuthorFacade,
                           ResponseErrorValidation responseErrorValidation
    ) {
        this.userGroupService = userGroupService;
        this.userService = userService;
        this.userGroupFacade = userGroupFacade;
        this.userGroupAuthorFacade = userGroupAuthorFacade;
        this.responseErrorValidation = responseErrorValidation;
    }

    //createUserGroup(UserGroupDTO userGroupDTO, Principal principal)
    @PostMapping("/create")
    public ResponseEntity<Object> createUserGroup(@Valid @RequestBody UserGroupDTO userGroupDTO,
                                             BindingResult bindingResult,
                                             Principal principal) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        UserGroup userGroup = userGroupService.createUserGroup(userGroupDTO, principal);
        User viewUser = userService.getCurrentUser(principal);
        UserGroupDTO createdUserGroupDTO = userGroupFacade.userGroupToUserGroupDTO(userGroup, viewUser);

        return new ResponseEntity<>(createdUserGroupDTO, HttpStatus.OK);
    }

    //getAllUserGroups()
    @GetMapping("/all")
    public ResponseEntity<List<UserGroupAuthorDTO>> getAllUserGroups(@RequestParam(required = false) String nameSearch,
                                                                     @RequestParam(required = false) String page,
                                                                     @RequestParam(required = false) String size,
                                                                     Principal principal
    ) throws IOException {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        List<UserGroupAuthorDTO> userAuthorDTOList;
        if(nameSearch == null) {
            userAuthorDTOList = userGroupService.getAllUserGroups(pageable)
                    .stream()
                    .map(userGroupAuthorFacade::userGroupToUserGroupAuthorDTO)
                    .collect(Collectors.toList());
        } else {
            userAuthorDTOList = userGroupService.searchUserGroups(nameSearch, pageable)
                    .stream()
                    .map(userGroupAuthorFacade::userGroupToUserGroupAuthorDTO)
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(userAuthorDTOList, HttpStatus.OK);
    }
    @GetMapping("/count/all")
    public ResponseEntity<Long> countAllUserGroups(@RequestParam(required = false) String nameSearch) throws IOException {
        Long postsAmount;
        if(nameSearch == null) {
            postsAmount = userGroupService.countAllUserGroups();
        } else {
            postsAmount = userGroupService.countSearchUserGroups(nameSearch);
        }
        return new ResponseEntity<>(postsAmount, HttpStatus.OK);
    }

    @GetMapping("/{userGroupId}")
    public ResponseEntity<Object> getUserGroup(@PathVariable("userGroupId") String userGroupId, Principal principal) {
        try {
            User viewUser = userService.getCurrentUser(principal);
            UserGroup userGroup = userGroupService.getUserGroupById(Long.parseLong(userGroupId));
            UserGroupDTO userGroupDTO = userGroupFacade.userGroupToUserGroupDTO(userGroup, viewUser);
            return new ResponseEntity<>(userGroupDTO, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            String errorIdMessage = "User with id: " + userGroupId + " not found";
            errorMap.put("userIdError", errorIdMessage);
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
    }

    //updateUserGroup(UserGroupDTO userGroupDTO, Principal principal)
    @PatchMapping("/{userGroupId}")
    public  ResponseEntity<Object> updateUserGroup(@Valid @RequestBody UserGroupDTO userGroupDTO, @PathVariable("userGroupId") String userGroupId, BindingResult bindingResult, Principal principal) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        UserGroup userGroup = userGroupService.updateUserGroup(userGroupDTO, principal);
        User viewUser = userService.getCurrentUser(principal);
        UserGroupDTO userUpdated = userGroupFacade.userGroupToUserGroupDTO(userGroup, viewUser);
        return new ResponseEntity<>(userUpdated,HttpStatus.OK);
    }

    //deleteUserGroup (Long userGroupId, Principal principal)
    @DeleteMapping("/{userGroupId}")
    public ResponseEntity<MessageResponse> deleteUserGroup (@PathVariable("userGroupId") String userGroupId, Principal principal) {
        userGroupService.deleteUserGroup(Long.valueOf(userGroupId), principal);
        return new ResponseEntity<>(new MessageResponse("UserGroup was deleted"), HttpStatus.OK);
    }

}
