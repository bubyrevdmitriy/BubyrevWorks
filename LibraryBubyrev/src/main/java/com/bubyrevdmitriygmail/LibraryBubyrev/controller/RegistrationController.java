package com.bubyrevdmitriygmail.LibraryBubyrev.controller;

import com.bubyrevdmitriygmail.LibraryBubyrev.domain.User;
import com.bubyrevdmitriygmail.LibraryBubyrev.domain.dto.CaptchaResponseDto;
import com.bubyrevdmitriygmail.LibraryBubyrev.repos.UserRepo;
import com.bubyrevdmitriygmail.LibraryBubyrev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL= "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${recaptcha.secret}")
    private  String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirm,
                          @RequestParam("g-recaptcha-response") String captchaResponse,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        String url = String.format( CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if(!response.isSuccess()) {
            model.addAttribute( "captchaError","Пройдите проверку");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error","Повторите выбранный пароль");
        }

        if (user.getPassword() !=null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли различаются");
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
            Map <String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.addAllAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError","Пользователь с таким именем уже существует!");
            return "registration";
        }
        model.addAttribute("messageType", "primary");
        model.addAttribute("message", "Письмо с кодом активации выслано на вашу электроннную почту!");
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate (Model model, @PathVariable String code) {
        boolean isActivated = userService.ActivateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Пользователь успешно активирован!");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден!");
        }
        return "login";
    }
}
