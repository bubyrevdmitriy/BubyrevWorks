package com.example.demo.security;

public class SecurityConstants {

    //url для аутентификации
    public static final String SIGN_UP_URLS = "/api/auth/**";

    //параметры для генерации jwt токена
    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    //время истечения сессии
    public static final long EXPIRATION_TIME = 600_000_000; //10min
}
