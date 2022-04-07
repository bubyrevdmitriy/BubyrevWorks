package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Random;

@Service
public class UtilsService {
    public static final Logger LOG = LoggerFactory.getLogger(UtilsService.class);

    private final UserRepository userRepository;

    @Autowired
    public UtilsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email: " + email));
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with  id: " + id));
    }

    public String generatingRandomString(Integer length) {

        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);

        return randomString;
    }
}
