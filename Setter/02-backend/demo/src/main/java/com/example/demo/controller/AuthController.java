package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.payload.request.ChangePasswordRequest;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
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
import java.util.HashMap;
import java.util.Map;

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
    public AuthController(JWTTokenProvider jwtTokenProvider,
                          AuthenticationManager authenticationManager,
                          ResponseErrorValidation responseErrorValidation,
                          UserService userService
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.responseErrorValidation = responseErrorValidation;
        this.userService = userService;
    }

    // api/auth/signin endpoint  пользователь передает объект пользователь передает объект  request/LoginRequest
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        Map<String, String> errorMap;
        // check errors
        errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }


        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Map<String, String> errorMap1 = new HashMap<>();
            errorMap1.put("isCorrectData", "User data is not correct!");
            return  new ResponseEntity<>(errorMap1, HttpStatus.BAD_REQUEST);
        }


        if (userService.getUserByEmail(loginRequest.getEmail()).getEnabled()) {
            // задаем авторизацию
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // создаем токен
            String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

            // возвращаем токен
            return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
        } else {
            Map<String, String> errorMap1 = new HashMap<>();
            errorMap1.put("isUserEnabled", "User is not activated! Check our email!");
            return  new ResponseEntity<>(errorMap1, HttpStatus.BAD_REQUEST);
        }
    }

    // api/auth/signup endpoint пользователь передает объект  request/SingupRequest
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {

        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);

        User userFromDb = userService.getUserByEmail(signupRequest.getEmail());
        if (userFromDb != null)
        {
            String errorEmailMessage = "User with email: " + userFromDb.getEmail() + " already exists";
            if (errorMap == null) {
                errorMap = new HashMap<>();
            }
            errorMap.put("email", errorEmailMessage);
        }

        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        // create user
        userService.createUser(signupRequest);

        // message about successful registration
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/activateUser/{code}")
    public ResponseEntity<MessageResponse> activateUser (@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            return new ResponseEntity<>(new MessageResponse("Successfully activated new user!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("User was not activated, some error occurred"), HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/createNewPassword/{code}")
    public ResponseEntity<MessageResponse> checkUserPasswordRestoreCode (@PathVariable String code) {
        boolean isCodeCorrect = userService.checkUserPasswordRestoreCode(code);
        if (isCodeCorrect) {
            System.out.println(isCodeCorrect);
            return new ResponseEntity<>(new MessageResponse("Password Restore Code is correct!"), HttpStatus.OK);
        } else {
            System.out.println(isCodeCorrect);
            return new ResponseEntity<>(new MessageResponse("Password Restore Code is not correct"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createNewPassword/{code}")
    public ResponseEntity<MessageResponse> changeUserPassword (@PathVariable String code, @Valid @RequestBody ChangePasswordRequest changePasswordRequest, BindingResult bindingResult) {
        // check errors
        Map<String, String> errorMap = responseErrorValidation.mapValidationService(bindingResult);
        //return errors
        if (!ObjectUtils.isEmpty(errorMap)) {
            return  new ResponseEntity<>(new MessageResponse("Passwords not correct!"), HttpStatus.BAD_REQUEST);
        }
        System.out.println("public ResponseEntity<Object> changeUserPassword (@PathVariable String code, @Valid @RequestBody ChangePasswordRequest changePasswordRequest, BindingResult bindingResult) {");
        boolean passwordChanged = userService.changeUserPassword(code, changePasswordRequest);
        if (passwordChanged) {
            System.out.println(passwordChanged);
            return new ResponseEntity<>(new MessageResponse("Password successfully changed!"), HttpStatus.OK);
        } else {
            System.out.println(passwordChanged);
            return new ResponseEntity<>(new MessageResponse("Password was not changed, some error occurred"), HttpStatus.BAD_REQUEST);
        }
    }



}
