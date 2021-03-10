package com.bubyrevdmitriygmail.LibraryBubyrev.service;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.Role;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user==null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }

    private void sendMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте!, %s! \n" +
                            "Добро пожаловать в LibraryBubyrev. Для завершения регистрации, проследуйте по ссылке ниже: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCodeEmail()
            );

            mailSender.send( user.getEmail(), "Activation code", message);
        }
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb !=null) {

            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCodeEmail(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sendMessage(user);

        userRepo.save(user);

        return true;
    }

    public boolean ActivateUser(String code) {
        User user = userRepo.findByActivationCodeEmail(code);

        if (user == null) {
            return false;
        }

        user.setActivationCodeEmail(null);

        userRepo.save(user);

        return true;
    }

    public Iterable<User> findAll() {
           return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(User userBase,  String firstName, String middleName, String lastName, String email, String phone, String password, MultipartFile file) throws IOException {
        User user = userRepo.findByUsername(userBase.getUsername());
        String userEmail = user.getEmail();

        /*boolean isEmailChanged = (email !=null && !email.equals(userEmail)) ||
                (userEmail !=null && !userEmail.equals(email));*/

        boolean isEmailChanged=false;

        if(email != null) {
            if(userEmail != null && !userEmail.equals(email)) {isEmailChanged =true;}

            if(userEmail == null ) {isEmailChanged =true;}
        }


        if(isEmailChanged) {
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)) {
                user.setActivationCodeEmail(UUID.randomUUID().toString());
            }
        }

        if(firstName!= null && !StringUtils.isEmpty(firstName)) {
            user.setFirstName(firstName);
        }

        if(!StringUtils.isEmpty(middleName)) {
            user.setMiddleName(middleName);
        }

        if(!StringUtils.isEmpty(lastName)) {
            user.setLastName(lastName);
        }

        if(!StringUtils.isEmpty(password)) {
            //user.setPassword(password);
            user.setPassword(passwordEncoder.encode(password));
        }

        if(!StringUtils.isEmpty(phone)) {
            user.setPhone(phone);
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile +"."+file.getOriginalFilename();

            //file.transferTo(new File(resultFilename));
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            user.setFilename(resultFilename);
        }
        userRepo.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }


    }
}
