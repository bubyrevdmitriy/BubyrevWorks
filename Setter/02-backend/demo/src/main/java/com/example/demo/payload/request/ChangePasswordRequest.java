package com.example.demo.payload.request;

import com.example.demo.annotation.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class ChangePasswordRequest {
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6)
    private String password;
    @NotEmpty(message = "Password confirmation cannot be empty")
    @Size(min = 6)
    private String confirmPassword;
}
