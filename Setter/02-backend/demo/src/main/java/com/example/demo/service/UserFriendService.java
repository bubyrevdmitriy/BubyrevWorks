package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserFriendInvite;
import com.example.demo.entity.UserFriendPair;
import com.example.demo.repository.UserFriendInviteRepository;
import com.example.demo.repository.UserFriendPairRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserFriendService {
    public static final Logger LOG = LoggerFactory.getLogger(UserFriendService.class);

    private final UserFriendInviteRepository userFriendInviteRepository;
    private final UserFriendPairRepository userFriendPairRepository;
    private final UserRepository userRepository;
    private final UtilsService utilsService;

    @Autowired
    public UserFriendService(UserFriendInviteRepository userFriendInviteRepository,
                             UserFriendPairRepository userFriendPairRepository,
                            UserRepository userRepository,
                            UtilsService utilsService) {
        this.userFriendInviteRepository = userFriendInviteRepository;
        this.userFriendPairRepository = userFriendPairRepository;
        this.userRepository = userRepository;
        this.utilsService = utilsService;
    }

    public Boolean inviteUserToMyFriend(Long recipientUserId, Principal principal) {
        User senderUser = utilsService.getUserByPrincipal(principal);
        User recipientUser = utilsService.getUserById(recipientUserId);

        UserFriendInvite userFriendInviteExist;
        UserFriendPair userFriendPairExist;
        try {
            userFriendInviteExist = getFriendInviteByUsers(senderUser, recipientUser);
        } catch (Exception e) {
            userFriendInviteExist = null;
        }
        try {
            userFriendPairExist = getFriendPairByUsers(senderUser, recipientUser);
        } catch (Exception e) {
            userFriendPairExist = null;
        }

        if(userFriendInviteExist == null && userFriendPairExist == null) {
            UserFriendInvite userFriendInvite = new UserFriendInvite();
            userFriendInvite.setSenderUser(senderUser);
            userFriendInvite.setRecipientUser(recipientUser);

            if (senderUser.getFriendInvitesSendAmount() == null) {
                senderUser.setFriendInvitesSendAmount(1);
            } else {
                senderUser.setFriendInvitesSendAmount(senderUser.getFriendInvitesSendAmount()+1);
            }
            if (recipientUser.getFriendInvitesReceivedAmount() == null) {
                recipientUser.setFriendInvitesReceivedAmount(1);
            } else {
                recipientUser.setFriendInvitesReceivedAmount(recipientUser.getFriendInvitesReceivedAmount()+1);
            }

            userRepository.save(senderUser);
            userRepository.save(recipientUser);
            userFriendInviteRepository.save(userFriendInvite);
            LOG.info("Saving FriendInvite for Users: {} {}", senderUser.getId(), recipientUser.getId());
            return true;
        }
        return false;
    }

    public Boolean denyInvitation(Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserFriendInvite userFriendInvite;
        try {
            userFriendInvite = getFriendInviteByUsers(currentUser, otherUser);
            User senderUser = userFriendInvite.getSenderUser();
            User recipientUser = userFriendInvite.getRecipientUser();
            if (currentUser.getId().equals(senderUser.getId()) || currentUser.getId().equals(recipientUser.getId())) {
                senderUser.setFriendInvitesSendAmount(senderUser.getFriendInvitesSendAmount()-1);
                recipientUser.setFriendInvitesReceivedAmount(recipientUser.getFriendInvitesReceivedAmount()-1);
                userRepository.save(senderUser);
                userRepository.save(recipientUser);
                userFriendInviteRepository.delete(userFriendInvite);
                LOG.info("Delete FriendInvite for Users: {} and {}. FriendInvite denied!", senderUser.getId(), recipientUser.getId());
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    public Boolean acceptInvitation(Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserFriendInvite userFriendInvite;
        try {
            userFriendInvite = getFriendInviteByUsers(currentUser, otherUser);
            User senderUser = userFriendInvite.getSenderUser();
            User recipientUser = userFriendInvite.getRecipientUser();

            if (currentUser.getId().equals(recipientUser.getId())) {
                senderUser.setFriendInvitesSendAmount(senderUser.getFriendInvitesSendAmount()-1);
                recipientUser.setFriendInvitesReceivedAmount(recipientUser.getFriendInvitesReceivedAmount()-1);

                if (senderUser.getUserFriendsAmount() == null) {
                    senderUser.setUserFriendsAmount(1);
                } else {
                    senderUser.setUserFriendsAmount(senderUser.getUserFriendsAmount()+1);
                }
                if (recipientUser.getUserFriendsAmount() == null) {
                    recipientUser.setUserFriendsAmount(1);
                } else {
                    recipientUser.setUserFriendsAmount(recipientUser.getUserFriendsAmount()+1);
                }

                UserFriendPair userFriendPair = new UserFriendPair();
                userFriendPair.setSenderUser(senderUser);
                userFriendPair.setRecipientUser(recipientUser);

                userRepository.save(senderUser);
                userRepository.save(recipientUser);
                userFriendPairRepository.save(userFriendPair);
                userFriendInviteRepository.delete(userFriendInvite);
                LOG.info("Delete FriendInvite for Users: {} {}. FriendPair created!", senderUser.getId(), recipientUser.getId());
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    public Boolean endFriendship(Long userId, Principal principal) {
        User currentUser = utilsService.getUserByPrincipal(principal);
        User otherUser = utilsService.getUserById(userId);
        UserFriendPair userFriendPair;
        try {
            userFriendPair = getFriendPairByUsers(currentUser, otherUser);
            User senderUser = userFriendPair.getSenderUser();
            User recipientUser = userFriendPair.getRecipientUser();

            if (currentUser.getId().equals(senderUser.getId()) || currentUser.getId().equals(recipientUser.getId())) {
                currentUser.setUserFriendsAmount(currentUser.getUserFriendsAmount()-1);
                recipientUser.setUserFriendsAmount(recipientUser.getUserFriendsAmount()-1);
                userRepository.save(currentUser);
                userRepository.save(recipientUser);
                userFriendPairRepository.delete(userFriendPair);
                LOG.info("Delete Friendship for Users: {} {}", currentUser.getId(), recipientUser.getId());
                return true;
            }
        } catch (Exception e) {}
        return false;
    }

    public  List<UserFriendInvite> getUserFriendInvitesReceivedFromUser(Long userId, Principal principal) {
        User user = utilsService.getUserById(userId);
        return userFriendInviteRepository.findAllByRecipientUser(user);
    }

    public  List<UserFriendInvite> getUserFriendInvitesSendFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userFriendInviteRepository.findAllBySenderUser(user);
    }

    public  List<UserFriendInvite> getAllUserFriendInvitesFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userFriendInviteRepository.findAllBySenderUserOrRecipientUser(user, user);
    }

    public  List<UserFriendPair> getUserFriendPairsFromUser(Long userId) {
        User user = utilsService.getUserById(userId);
        return userFriendPairRepository.findAllBySenderUserOrRecipientUser(user, user);
    }

    public UserFriendInvite getFriendInviteByUsers(User user1, User user2) {
        return userFriendInviteRepository.findBySenderUserAndRecipientUser(user1, user2)
                .orElse(userFriendInviteRepository.findBySenderUserAndRecipientUser(user2, user1)
                        .orElse(null));
    }

    public UserFriendInvite getFriendInviteBySenderAndRecipient(User user1, User user2) {
        return userFriendInviteRepository.findBySenderUserAndRecipientUser(user1, user2).orElse(null);
    }

    public UserFriendPair getFriendPairByUsers(User user1, User user2) {
        return userFriendPairRepository.findBySenderUserAndRecipientUser(user1, user2)
                .orElse(userFriendPairRepository.findBySenderUserAndRecipientUser(user2, user1)
                        .orElse(null));
    }

}
