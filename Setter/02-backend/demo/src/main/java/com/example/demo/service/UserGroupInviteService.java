package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserGroupInviteService {
    public static final Logger LOG = LoggerFactory.getLogger(UserGroupInviteService.class);

    private final UserGroupRepository userGroupRepository;
    private final UserGroupInviteRepository userGroupInviteRepository;
    private final UserGroupPairRepository userGroupPairRepository;
    private final UserRepository userRepository;
    private final UserGroupService userGroupService;
    private final UtilsService utilsService;

    @Autowired
    public UserGroupInviteService(UserGroupRepository userGroupRepository,
                                  UserGroupInviteRepository userGroupInviteRepository,
                                  UserGroupPairRepository userGroupPairRepository,
                                  UserRepository userRepository,
                                  UserGroupService userGroupService,
                                  UtilsService utilsService
    ) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupInviteRepository = userGroupInviteRepository;
        this.userGroupPairRepository = userGroupPairRepository;
        this.userRepository = userRepository;
        this.userGroupService = userGroupService;
        this.utilsService = utilsService;
    }

    public Boolean inviteUserToGroup(Long userGroupId, Long recipientUserId, Principal principal) {
        User senderUser = utilsService.getUserByPrincipal(principal);
        User recipientUser = utilsService.getUserById(recipientUserId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupInvite userGroupInviteExist = getUserGroupInviteByUsersAndUserGroup(senderUser, recipientUser, userGroup);
        UserGroupPair userGroupPairExist = getUserGroupPairByUserAndGroup(recipientUser, userGroup);

        if(userGroupInviteExist == null && userGroupPairExist == null) {
            UserGroupInvite userGroupInvite = new UserGroupInvite();
            userGroupInvite.setSenderUser(senderUser);
            userGroupInvite.setRecipientUser(recipientUser);
            userGroupInvite.setUserGroup(userGroup);

            if (senderUser.getGroupInvitesSendAmount() == null) {
                senderUser.setGroupInvitesSendAmount(1);
            } else {
                senderUser.setGroupInvitesSendAmount(senderUser.getGroupInvitesSendAmount()+1);
            }
            if (recipientUser.getGroupInvitesReceivedAmount() == null) {
                recipientUser.setGroupInvitesReceivedAmount(1);
            } else {
                recipientUser.setGroupInvitesReceivedAmount(recipientUser.getGroupInvitesReceivedAmount()+1);
            }

            userRepository.save(senderUser);
            userRepository.save(recipientUser);
            userGroupInviteRepository.save(userGroupInvite);
            LOG.info("Saving GroupInvite for Users: {} {}. And UserGroup {}", senderUser.getId(), recipientUser.getId(), userGroup.getId());
            return true;
        }
        return false;
    }

    public UserGroupPair getUserGroupPairByUserAndGroup(Long userGroupId, Long userId) {
        User user = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair;
        try {
            userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
            return userGroupPair;
        } catch (Exception e) {}
        return null;
    }

    public Boolean denyUserGroupInvitation(Long userGroupId, Principal principal) {
        User recipientUser = utilsService.getUserByPrincipal(principal);
        User senderUser;
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        List<UserGroupInvite> userGroupInvites;
        System.out.println("denyUserGroupInvitation");
        try {
            userGroupInvites = findAllInvitesByRecipientUserAndUserGroup(recipientUser, userGroup);
            for (UserGroupInvite userGroupInvite : userGroupInvites) {
                senderUser = userGroupInvite.getSenderUser();
                senderUser.setGroupInvitesSendAmount(senderUser.getGroupInvitesSendAmount()-1);
                recipientUser.setGroupInvitesReceivedAmount(recipientUser.getGroupInvitesReceivedAmount()-1);
                userRepository.save(senderUser);
                userRepository.save(recipientUser);
                userGroupInviteRepository.delete(userGroupInvite);
                LOG.info("Delete GroupInvite for Users: {} and {}. And UserGroup {}. GroupInvite denied!", senderUser.getId(), recipientUser.getId(), userGroupInvite.getId());
            }
            return true;
        } catch (Exception e) {}
        return false;
    }

    public Boolean denyUserGroupInvitation(Long userGroupId, Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupInvite userGroupInvite;
        System.out.println("denyUserGroupInvitation");
        try {
            userGroupInvite = getUserGroupInviteByUsersAndUserGroup(currentUser, otherUser, userGroup);
            User senderUser = userGroupInvite.getSenderUser();
            User recipientUser = userGroupInvite.getRecipientUser();
            if (currentUser.getId().equals(senderUser.getId()) || currentUser.getId().equals(recipientUser.getId())) {
                senderUser.setGroupInvitesSendAmount(senderUser.getGroupInvitesSendAmount()-1);
                recipientUser.setGroupInvitesReceivedAmount(recipientUser.getGroupInvitesReceivedAmount()-1);
                userRepository.save(senderUser);
                userRepository.save(recipientUser);
                userGroupInviteRepository.delete(userGroupInvite);
                LOG.info("Delete GroupInvite for Users: {} and {}. And UserGroup {}. GroupInvite denied!", senderUser.getId(), recipientUser.getId(), userGroupInvite.getId());
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Boolean acceptGroupInvitation(Long userGroupId, Principal principal) {
        User recipientUser = utilsService.getUserByPrincipal(principal);
        User senderUser;
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        List<UserGroupInvite> userGroupInvites;
        System.out.println("denyUserGroupInvitation");
        try {
            userGroupInvites = findAllInvitesByRecipientUserAndUserGroup(recipientUser, userGroup);
            UserGroupInvite userGroupInviteToEnter =  userGroupInvites.get(0);
            Boolean result = enterToGroupDeleteGroupInvite(recipientUser, userGroup, userGroupInviteToEnter);
            for (UserGroupInvite userGroupInvite : userGroupInvites) {
                senderUser = userGroupInvite.getSenderUser();
                senderUser.setGroupInvitesSendAmount(senderUser.getGroupInvitesSendAmount()-1);
                recipientUser.setGroupInvitesReceivedAmount(recipientUser.getGroupInvitesReceivedAmount()-1);
                userRepository.save(senderUser);
                userRepository.save(recipientUser);
                userGroupInviteRepository.delete(userGroupInvite);
                LOG.info("Delete GroupInvite for Users: {} and {}. And UserGroup {}. GroupInvite denied!", senderUser.getId(), recipientUser.getId(), userGroupInvite.getId());
            }
            return result;
        } catch (Exception e) {}
        return false;
    }

    public Boolean acceptGroupInvitation(Long userGroupId, Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupInvite userGroupInvite;
        try {
            userGroupInvite = getUserGroupInviteByUsersAndUserGroup(currentUser, otherUser, userGroup);
            return enterToGroupDeleteGroupInvite(currentUser, userGroup, userGroupInvite);
        } catch (Exception e) {}
        return false;
    }

    public Boolean endGroupMembership(Long userGroupId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair;
        try {
            userGroupPair = getUserGroupPairByUserAndGroup(currentUser, userGroup);
            User user = userGroupPair.getUser();

            if (currentUser.getId().equals(user.getId())) {
                currentUser.setUserGroupsAmount(currentUser.getUserGroupsAmount()-1);
                userGroup.setUserMembershipAmount(userGroup.getUserMembershipAmount()-1);
                userRepository.save(currentUser);
                userGroupRepository.save(userGroup);
                userGroupPairRepository.delete(userGroupPair);
                LOG.info("Delete GroupMembership for User{} and group {}", currentUser.getId(), userGroup.getId());
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    public Boolean makeNewGroupAdmin(Long userGroupId, Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair;
        try {
            userGroupPair = getUserGroupPairByUserAndGroup(currentUser, userGroup);

            if (userGroupPair.getIsAdmin()) {
                UserGroupPair userGroupPairModifyToAdmin = getUserGroupPairByUserAndGroup(otherUser, userGroup);

                if (userGroupPairModifyToAdmin != null && userGroupPair.getIsAdmin()) {
                    userGroupPairModifyToAdmin.setIsAdmin(true);
                    userGroupPairRepository.save(userGroupPairModifyToAdmin);
                    return true;
                }
            }
        } catch (Exception e) {}
        return false;
    }

    public Boolean endGroupAdmin(Long userGroupId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair;
        try {
            userGroupPair = getUserGroupPairByUserAndGroup(currentUser, userGroup);

            if (userGroupPair != null && userGroupPair.getIsAdmin()) {
                List<UserGroupPair> userGroupPairList = getUserGroupPairsFromUserGroup(userGroup);
                userGroupPair.setIsAdmin(false);
                userGroupPairRepository.save(userGroupPair);
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    public List<UserGroupInvite> getUserGroupInvitesSendFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userGroupInviteRepository.findAllBySenderUser(user);
    }

    public  List<UserGroupInvite> getUserGroupInvitesRecievedFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userGroupInviteRepository.findAllByRecipientUser(user);
    }

    public  List<UserGroupPair> getUserGroupPairsFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userGroupPairRepository.findAllByUser(user);
    }

    public  List<UserGroupInvite> getAllUserGroupInvitesFromUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return userGroupInviteRepository.findAllByUserGroup(userGroup);
    }

    public  List<UserGroupPair> getUserGroupPairsFromUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return userGroupPairRepository.findAllByUserGroup(userGroup);
    }

    public  List<UserGroupPair> getUserGroupPairsFromUserGroup(UserGroup userGroup) {
        return userGroupPairRepository.findAllByUserGroup(userGroup);
    }

    public  List<UserGroupInvite> findAllInvitesByRecipientUserAndUserGroup(User senderUser, UserGroup userGroup) {
        return userGroupInviteRepository.findAllByRecipientUserAndUserGroup(senderUser, userGroup);
    }

    public UserGroupInvite getUserGroupInviteByUsersIdAndUserGroupId(Long userId1, Long userId2, Long userGroupId) {
        User user1 = utilsService.getUserById(userId1);
        User user2 = utilsService.getUserById(userId2);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return userGroupInviteRepository.findBySenderUserAndRecipientUserAndUserGroup(user1, user2, userGroup)
                .orElse(userGroupInviteRepository.findBySenderUserAndRecipientUserAndUserGroup(user2, user1, userGroup)
                        .orElse(null));
    }

    public UserGroupInvite getUserGroupInviteByUsersAndUserGroup(User user1, User user2, UserGroup userGroup) {
        return userGroupInviteRepository.findBySenderUserAndRecipientUserAndUserGroup(user1, user2, userGroup)
                .orElse(userGroupInviteRepository.findBySenderUserAndRecipientUserAndUserGroup(user2, user1, userGroup)
                        .orElse(null));
    }

    public UserGroupInvite getUserGroupInviteByRecipientUserAndUserGroup(User user, UserGroup userGroup) {
        return userGroupInviteRepository.findByRecipientUserAndUserGroup(user, userGroup)
                        .orElse(null);
    }

    public UserGroupPair getUserGroupPairByUserIdAndGroupId(Long userId, Long userGroupId) {
        User user = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return userGroupPairRepository.findByUserAndUserGroup(user, userGroup)
                .orElse(null);
    }

    public UserGroupPair getUserGroupPairByUserAndGroup(User user, UserGroup userGroup) {
        return userGroupPairRepository.findByUserAndUserGroup(user, userGroup)
                .orElse(null);
    }

    public boolean isUserAdminInUserGroup(User user, UserGroup userGroup) {
        UserGroupPair userGroupPair;
        try {
            userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
            if(userGroupPair.getIsAdmin()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserAdminInUserGroup(Long userId, Long userGroupId) {
        User user = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
        return userGroupPair != null && userGroupPair.getIsAdmin();
    }

    public Boolean isGroupAdmin(Long userGroupId, Principal principal) {
        User user = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        return isUserAdminInUserGroup(user,userGroup);
    }

    public boolean isUserMemberInUserGroup(User user, UserGroup userGroup) {
        UserGroupPair userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
        return userGroupPair != null;
    }

    public boolean isUserMemberInUserGroup(Long userId, Long userGroupId) {
        User user = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair = getUserGroupPairByUserAndGroup(user, userGroup);
        return userGroupPair != null;
    }

    public boolean isUserHasInviteUserGroup(User user, UserGroup userGroup) {
        UserGroupInvite userGroupInvite = getUserGroupInviteByRecipientUserAndUserGroup(user, userGroup);
        if (userGroupInvite != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserHasInviteUserGroup(Long userId, Long userGroupId) {
        User user = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupInvite userGroupInvite = getUserGroupInviteByRecipientUserAndUserGroup(user, userGroup);
        if (userGroupInvite != null) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean enterToGroup(Long userGroupId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupInvite userGroupInvite = null;

        try {
            userGroupInvite = getUserGroupInviteByRecipientUserAndUserGroup(currentUser, userGroup);
        } catch (Exception e) {}

        if (userGroupInvite != null) {
            return enterToGroupDeleteGroupInvite(currentUser, userGroup, userGroupInvite);
        } else {
            if (userGroup.getUserMembershipAmount() == null) {
                userGroup.setUserMembershipAmount(1);
            } else {
                userGroup.setUserMembershipAmount(userGroup.getUserMembershipAmount() + 1);
            }

            if (currentUser.getUserGroupsAmount() == null) {
                currentUser.setUserGroupsAmount(1);
            } else {
                currentUser.setUserGroupsAmount(currentUser.getUserGroupsAmount() + 1);
            }

            UserGroupPair userGroupPair = new UserGroupPair();
            userGroupPair.setUser(currentUser);
            userGroupPair.setUserGroup(userGroup);
            userGroupPair.setIsAdmin(false);

            userRepository.save(currentUser);
            userGroupPairRepository.save(userGroupPair);
            userGroupRepository.save(userGroup);
            return true;
        }
    }

    public Boolean enterToGroupDeleteGroupInvite(User currentUser, UserGroup userGroup, UserGroupInvite userGroupInvite) {
        User senderUser = userGroupInvite.getSenderUser();
        User recipientUser = userGroupInvite.getRecipientUser();

        if (currentUser.getId().equals(recipientUser.getId())) {
            senderUser.setGroupInvitesSendAmount(senderUser.getGroupInvitesSendAmount() - 1);
            recipientUser.setGroupInvitesReceivedAmount(recipientUser.getGroupInvitesReceivedAmount() - 1);

            if (userGroup.getUserMembershipAmount() == null) {
                userGroup.setUserMembershipAmount(1);
            } else {
                userGroup.setUserMembershipAmount(userGroup.getUserMembershipAmount() + 1);
            }

            if (recipientUser.getUserGroupsAmount() == null) {
                recipientUser.setUserGroupsAmount(1);
            } else {
                recipientUser.setUserGroupsAmount(recipientUser.getUserGroupsAmount() + 1);
            }

            UserGroupPair userGroupPair = new UserGroupPair();
            userGroupPair.setUser(currentUser);
            userGroupPair.setUserGroup(userGroup);
            userGroupPair.setIsAdmin(false);

            userRepository.save(senderUser);
            userRepository.save(recipientUser);
            userGroupPairRepository.save(userGroupPair);
            userGroupInviteRepository.delete(userGroupInvite);
            userGroupRepository.save(userGroup);
            LOG.info("Delete GroupInvite for Users: {} and {}. And UserGroup {}. GroupPair created!", senderUser.getId(), recipientUser.getId(), userGroupInvite.getId());
            return true;
        }
        return false;
    }

    public List<UserGroup> getUserGroupAvailableToInviteForUser(Long userId, Principal principal) {
        User senderUser = utilsService.getUserByPrincipal(principal);
        User recipientUser = utilsService.getUserById(userId);
        List<UserGroupPair> userGroupPairsFromSenderUser = userGroupPairRepository.findAllByUser(senderUser);
        List<UserGroupPair> userGroupPairsFromRecipientUser = userGroupPairRepository.findAllByUser(recipientUser);
        List<UserGroupInvite> userGroupInvitesFromRecipientUser = userGroupInviteRepository.findAllByRecipientUser(recipientUser);
        List<UserGroup> userGroupAvailableToInviteForRecipientUser = new ArrayList<>();
        for (UserGroupPair groupPairFromSenderUser : userGroupPairsFromSenderUser) {
            boolean isRecipientUserHasPair = false;
            boolean isRecipientUserHasInvite = false;
            for (UserGroupPair groupPairFromRecipientUser : userGroupPairsFromRecipientUser) {
                if (groupPairFromSenderUser.getId() == groupPairFromRecipientUser.getId()) {
                    isRecipientUserHasPair = true;
                    break;
                }
            }
            for (UserGroupInvite groupInviteFromRecipientUser : userGroupInvitesFromRecipientUser) {
                if (groupPairFromSenderUser.getId() == groupInviteFromRecipientUser.getId()) {
                    isRecipientUserHasInvite = true;
                    break;
                }
            }
            if(!isRecipientUserHasPair && !isRecipientUserHasInvite) {
                userGroupAvailableToInviteForRecipientUser.add(groupPairFromSenderUser.getUserGroup());
            }
        }
        return userGroupAvailableToInviteForRecipientUser;
    }

    public List<UserGroupPair> getUserGroupPairAdminsFromUserGroup(Long userGroupId) {
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        List<UserGroupPair> userGroupPairs = userGroupPairRepository.findAllByUserGroup(userGroup);
        List<UserGroupPair> userGroupPairAdmins = new ArrayList<>();
        for(UserGroupPair userGroupPair : userGroupPairs) {
            if (userGroupPair.getIsAdmin()) {
                userGroupPairAdmins.add(userGroupPair);
            }
        }
        return userGroupPairAdmins;
    }


    public Boolean kickUserFromGroup(Long userGroupId, Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserGroup userGroup = userGroupService.getUserGroupById(userGroupId);
        UserGroupPair userGroupPair;

        try {
            userGroupPair = getUserGroupPairByUserAndGroup(currentUser, userGroup);

            if (userGroupPair.getIsAdmin()) {
                UserGroupPair userGroupPairModifyToAdmin = getUserGroupPairByUserAndGroup(otherUser, userGroup);

                if (userGroupPairModifyToAdmin != null && userGroupPair.getIsAdmin()) {
                    otherUser.setUserGroupsAmount(otherUser.getUserGroupsAmount()-1);
                    userGroup.setUserMembershipAmount(userGroup.getUserMembershipAmount()-1);
                    userGroupPairRepository.delete(getUserGroupPairByUserAndGroup(otherUser, userGroup));
                    userRepository.save(otherUser);
                    userGroupRepository.save(userGroup);
                    LOG.info("Delete GroupMembership for User{} and group {}", otherUser.getId(), userGroup.getId());
                    return true;
                }
            }
        } catch (Exception e) {}
        return false;
    }
}
