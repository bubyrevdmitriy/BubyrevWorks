package com.example.demo.controller;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignUpRequest;
import com.example.demo.payload.response.JWTTokenSuccessResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.security.JWTTokenProvider;
import com.example.demo.security.SecurityConstants;
import com.example.demo.service.UserService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;
    private final UserService userService;

    @Autowired
    public AuthController(JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, ResponseErrorValidation responseErrorValidation, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.responseErrorValidation = responseErrorValidation;
        this.userService = userService;
    }

    // api/auth/signin endpoint (регистрация) пользователь передает объект  request/LoginRequest
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                                   BindingResult bindingResult
    ) {
        // check errors
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        // задаем авторизацию
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // создаем токен
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        // возвращаем токен
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    // api/auth/signup endpoint (вход) пользователь передает объект  request/SingupRequest
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                               BindingResult bindingResult
    ) {
        System.out.println("public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest1");
        // check errors (несовпадение паролей, неуникальный email, пароли недостаточной длинны)
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        //return errors if exists
        if (!ObjectUtils.isEmpty(errors)) return errors;
        // create user
        userService.createUser(signUpRequest);
        // message about successful registration
        System.out.println("public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest2");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/sendPasswordRestoreLetter")
    public ResponseEntity<MessageResponse> sendPasswordRestoreLetter(@RequestBody String email) {
        boolean result = userService.createUserPasswordRestoreCode(email);
        if (result) {
            return new ResponseEntity<>(new MessageResponse("Password restore letter was send!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Password restore letter was not send, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }

}
