package com.example.demo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    //данный объект передается на сервер при попытки входа в систему
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
