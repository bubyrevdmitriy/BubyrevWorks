package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.ImageModel;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ERole;
import com.example.demo.exception.UserExistException;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UtilsService utilsService;
    private final CustomMailSender customMailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       UtilsService utilsService,
                       CustomMailSender customMailSender
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilsService = utilsService;
        this.customMailSender = customMailSender;
    }

    public User createUser(SignUpRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }


    // Principal principal - security object contains user information
    public User updateUser(UserDTO userDTO, Principal principal) {
        // объект ДТО хранит лишь необходимую часть полей класса юзер
        User user = utilsService.getUserByPrincipal(principal);
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setBio(userDTO.getBio());
        user.setUpdatedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void updateUserProfilePhoto(ImageModel imageModel, User user) {
        user.setProfileImage(imageModel);
        userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {
        return utilsService.getUserByPrincipal(principal);
    }

    public User getUserById(Long id) {
        return utilsService.getUserById(id);
    }

    public boolean createUserPasswordRestoreCode(String email) {
        User user = utilsService.getUserByEmail(email);
        String newPassword = utilsService.generatingRandomString(7);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedDate(LocalDateTime.now());
        sendMessageRestorePassword(user, newPassword);
        userRepository.save(user);
        return true;
    }

    private void sendMessageRestorePassword(User user, String newPassword) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте!, %s %s! \n" +
                            "Ваш новый пароль - %s",
                    user.getName(),
                    user.getLastname(),
                    newPassword
            );
            System.out.println(message);
            customMailSender.send(user.getEmail(), "InstaZoo: New Password", message);
        }
    }
}
