package com.example.demo.payload.request;

import com.example.demo.annotation.PasswordMatches;
import com.example.demo.annotation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignUpRequest {
    //данный объект передается на сервер при попытки регистрации
    @Email(message = "It should have email format")
    @NotEmpty(message = "Email cannot be empty")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastname;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6)
    private String password;
    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(min = 6)
    private String confirmPassword;
}
