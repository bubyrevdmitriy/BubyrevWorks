package com.example.demo.service;

import com.example.demo.dto.UserGroupDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.UserGroupPair;
import com.example.demo.exception.UserGroupNotFoundException;
import com.example.demo.repository.UserGroupPairRepository;
import com.example.demo.repository.UserGroupRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class UserGroupService {
    public static final Logger LOG = LoggerFactory.getLogger(UserGroupService.class);

    private final UserGroupRepository userGroupRepository;
    private final UserGroupPairRepository userGroupPairRepository;
    private final UserRepository userRepository;
    private final UtilsService utilsService;

    @Autowired
    public UserGroupService(UserGroupRepository userGroupRepository,
                            UserGroupPairRepository userGroupPairRepository,
                            UserRepository userRepository,
                            UtilsService utilsService) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupPairRepository = userGroupPairRepository;
        this.userRepository = userRepository;
        this.utilsService = utilsService;
    }

    public List<UserGroup> getAllUserGroups(Pageable pageable) {
        return userGroupRepository.findAllByOrderByCreatedDateDesc(pageable);
    }
    public Long countAllUserGroups() {return userGroupRepository.count();}

    public List<UserGroup> searchUserGroups(String searchValue, Pageable pageable) {
        return userGroupRepository.findByNameContainingOrderByCreatedDateDesc(searchValue, pageable);
    }
    public Long countSearchUserGroups(String searchValue) {
        return userGroupRepository.countByNameContaining(searchValue);
    }

    public UserGroup createUserGroup(UserGroupDTO userGroupDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = new UserGroup();
        userGroup.setName(userGroupDTO.getName());
        userGroup.setDescription(userGroupDTO.getDescription());
        userGroup.setUserMembershipAmount(1);
        UserGroup savedUserGroup = userGroupRepository.save(userGroup);
        UserGroupPair userGroupPair = new UserGroupPair();
        userGroupPair.setUser(user);
        userGroupPair.setUserGroup(savedUserGroup);
        userGroupPair.setIsAdmin(true);
        userGroupPairRepository.save(userGroupPair);

        if (user.getUserGroupsAmount() == null) {
            user.setUserGroupsAmount(1);
        } else {
            user.setUserGroupsAmount(user.getUserGroupsAmount() + 1);
        }
        userRepository.save(user);

        return savedUserGroup;
    }

    public UserGroup updateUserGroup(UserGroupDTO userGroupDTO, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = getUserGroupById(userGroupDTO.getId());
        UserGroupPair userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
        if (userGroupPair !=null && userGroupPair.getIsAdmin()) {
            userGroup.setName(userGroupDTO.getName());
            userGroup.setDescription(userGroupDTO.getDescription());
        }
        return userGroupRepository.save(userGroup);
    }

    @Transactional
    public void deleteUserGroup (Long userGroupId, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup =  getUserGroupById(userGroupId);
        UserGroupPair userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
        if (userGroupPair !=null && userGroupPair.getIsAdmin()) {
            user.setUserGroupsAmount(user.getUserGroupsAmount() - 1);
            userRepository.save(user);
            userGroupRepository.delete(userGroup);
        }
    }

    public UserGroup getUserGroupById(Long userGroupId) {
        return userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new UserGroupNotFoundException("UserGroup not found with id: " + userGroupId));
    }

    public UserGroupPair getUserGroupPairByUserAndGroup(User user, UserGroup userGroup) {
        return userGroupPairRepository.findByUserAndUserGroup(user, userGroup)
                .orElse(null);
    }
}
