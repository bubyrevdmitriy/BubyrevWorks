package com.example.demo.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    //данный объект выдается пользователю в случае проваленной авторизации
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid username";
        this.password = "Invalid Password";
    }
}
