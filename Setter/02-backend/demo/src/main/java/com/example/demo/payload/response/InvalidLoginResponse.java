package com.example.demo.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    // данный клас возвращается сервером в случае ошибки авторизации (статус 401)
    private String email;
    private String password;

    public InvalidLoginResponse() {
        this.email = "Invalid Email!1!";
        this.password = "Invalid Password!1!";
    }
}
