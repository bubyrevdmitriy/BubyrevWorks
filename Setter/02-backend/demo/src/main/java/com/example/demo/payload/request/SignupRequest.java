package com.example.demo.payload.request;

import com.example.demo.annotation.CustomValidEmail;
import com.example.demo.annotation.PasswordMatches;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@PasswordMatches
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotEmpty(message = "Email cannot be empty")
    //@CustomValidEmail
    private String email;
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstName;
    @NotEmpty(message = "Lastname cannot be empty")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6)
    private String password;
    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(min = 6)
    private String confirmPassword;

}
