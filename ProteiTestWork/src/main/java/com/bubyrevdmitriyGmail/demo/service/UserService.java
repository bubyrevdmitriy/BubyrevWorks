package com.bubyrevdmitriyGmail.demo.service;


import com.bubyrevdmitriyGmail.demo.exception.BadRequestException;
import com.bubyrevdmitriyGmail.demo.model.*;
import com.bubyrevdmitriyGmail.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserOnlineTimeService userOnlineTimeServiceImpl;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserStatusTimer userStatusTimer;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepo userRepo, UserOnlineTimeService userOnlineTimeServiceImpl, BCryptPasswordEncoder passwordEncoder, UserStatusTimer userStatusTimer, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.userOnlineTimeServiceImpl = userOnlineTimeServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.userStatusTimer = userStatusTimer;
        this.authenticationManager = authenticationManager;
    }

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public User register(User user) {
        User userFromDb = userRepo.findUserByUsername(user.getUsername());
        if (userFromDb !=null) {

            logger.info("IN register - user: {} already registered.", user.getUsername());
            throw new BadRequestException(user.getUsername() + " already registered.");

        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(Status.ACTIVE);

            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(UserRole.USER);
            user.setUserRoles(userRoles);

            user.setUserStatus(UserStatus.OFFLINE);
            user.setCreated(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            User registeredUser = userRepo.save(user);

            logger.info("IN register - user: {} successfully registered", registeredUser);

            return registeredUser;
        }
    }

    public List<User> getAll() {
        List<User> result = (List<User>) userRepo.findAll();
        logger.info("IN register - user: {} successfully registered", result.size());
        return result;
    }


    public User findByUsername(String username) {

        try {
            User result = userRepo.findUserByUsername(username);
            logger.info("IN findByUsername - user: {} found by username: {}", result, username);
            return result;
        } catch (Exception e) {
            throw new BadRequestException("User with username: "+username+" not found");
        }
    }

    public User findUserById(Long Id) {
        User result = userRepo.findById(Id).orElse(null);

        if (result == null) {
            logger.warn("IN findById - no user found by id: {}", Id);
            throw new BadRequestException("no user found by id: "+ Id);
        }

        logger.info("IN findById - user: {} found by id: {}", result, Id);
        return result;
    }

    public User updateUserStatus(Long id, UserStatus userStatus)  {

        User userFromDb = userRepo.findUserById(id);


            userFromDb.setUserStatus(userStatus);
            User registeredUser = userRepo.save(userFromDb);

            if (userStatus.name().equals("ONLINE")) {

                UserOnlineTime userOnlineTime = userOnlineTimeServiceImpl.createOrUpdate(userFromDb);
                userStatusTimer.setUserId(id);
                userStatusTimer.setUserOnlineTimeId( userOnlineTime.getId());

                userStatusTimer.timerStart();
            }
            return registeredUser;

    }

    public void delete(Long Id) {
        userRepo.deleteById(Id);
        logger.info("IN delete - user with id: {} successfully deleted", Id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = userRepo.findUserByUsername(username);
        logger.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User loginUser(String username, String password) {

        try {
            User user = findByUsername(username);
            if (user == null) {
                throw new BadRequestException("Invalid user name.");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info("IN login - user found by username: {} and succesfully authenticated", username);
            return user;

        } catch (AuthenticationException e) {
            throw new BadRequestException("Invalid user name and password combination.");
        }
    }

}
