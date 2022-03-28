package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.ERole;
import com.example.demo.exception.UserExistException;
import com.example.demo.payload.request.ChangePasswordRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Value("${hostnameFrontEnd}")
    private String hostname;


    private final UserRepository userRepository;
    private final UtilsService utilsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomMailSender customMailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       UtilsService utilsService,
                       BCryptPasswordEncoder passwordEncoder,
                       CustomMailSender customMailSender
    ) {
        this.userRepository = userRepository;
        this.utilsService = utilsService;
        this.passwordEncoder = passwordEncoder;
        this.customMailSender = customMailSender;
    }

    public List<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByCreatedDateDesc(pageable);
    }
    public Long countAllUsers() {return userRepository.count();}

    public List<User> searchUsers(String searchValue, Pageable pageable) {
        return userRepository.findByLastNameContainingOrderByCreatedDateDesc(searchValue, pageable);
    }
    public Long countSearchUsers(String searchValue) {
        return userRepository.countByLastNameContaining(searchValue);
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setFirstName(userIn.getFirstName());
        user.setLastName(userIn.getLastName());
        user.setBornDate(userIn.getBornDate());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);
        user.setEnabled(false);
        user.setActivationCodeEmail(UUID.randomUUID().toString());

        sendMessageActivateUser(user);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user with email" + user.getEmail() + " already exist. Please check credentials");
        }
    }

    public boolean activateUser(String code) {
        try {
            User user = getUserByActivationCodeEmail(code);
            user.setEnabled(true);
            user.setActivationCodeEmail(null);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean createUserPasswordRestoreCode(String email) {
        try {
            User user = getUserByEmail(email);
            System.out.println(user.getEmail());
            user.setPasswordRestoreCode(UUID.randomUUID().toString());
            userRepository.save(user);
            sendMessageRestorePassword(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean checkUserPasswordRestoreCode(String code) {
        try {
            User user = getUserByPasswordRestoreCode(code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean changeUserPassword(String code, ChangePasswordRequest changePasswordRequest) {
        boolean isCodeCorrect = checkUserPasswordRestoreCode(code);
        if (isCodeCorrect) {
            User user = getUserByPasswordRestoreCode(code);
            System.out.println(user.getEmail());
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            user.setPasswordRestoreCode(null);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    // Principal principal - security object contains user information

    public User updateUser(UserDTO userDTO, Principal principal) {
        // объект ДТО хранит лишь необходимую часть полей класса юзер
        User user = utilsService.getUserByPrincipal(principal);
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setBio(userDTO.getBio());
        user.setPhone(userDTO.getPhone());
        user.setBornDate(userDTO.getBornDate());

        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {
        return utilsService.getUserByPrincipal(principal);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with  id: " + id));
        //return userRepository.findUserById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public User getUserByActivationCodeEmail(String activationCodeEmail) {
        return userRepository.findUserByActivationCodeEmail(activationCodeEmail).orElse(null);
    }

    public User getUserByPasswordRestoreCode(String passwordRestoreCode) {
        return userRepository.findUserByPasswordRestoreCode(passwordRestoreCode).orElse(null);
    }

    private void sendMessageActivateUser(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте!, %s %s! \n" +
                            "Добро пожаловать в Setter. Для завершения регистрации, проследуйте по ссылке ниже: http://%s/activationUser/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    hostname,
                    user.getActivationCodeEmail()
            );
            System.out.println(message);
            customMailSender.send(user.getEmail(), "Setter: Activation code", message);
        }
    }

    private void sendMessageRestorePassword(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте!, %s %s! \n" +
                            "Для восстановления пароля, проследуйте по ссылке ниже: http://%s/createNewPassword/%s",
                    user.getFirstName(),
                    user.getLastName(),
                    hostname,
                    user.getPasswordRestoreCode()
            );
            System.out.println(message);
            customMailSender.send(user.getEmail(), "Setter: Password restore code", message);
        }
    }


}
