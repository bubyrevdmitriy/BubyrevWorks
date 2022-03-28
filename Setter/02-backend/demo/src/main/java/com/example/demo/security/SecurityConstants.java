package com.example.demo.security;

public class SecurityConstants {

    // авторизация на сервис будет проходить по этому url;
    // две ** означают, что далее может стоять люьой url
    public static final String SIGN_UP_URLS = "/api/auth/**";
    public static final String STREAM_UP_URLS = "/api/stream/**";
    public static final String CHAT_UP_URLS = "/chat/**";
    public static final String TOPIC_UP_URLS = "/topic/**";

    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    // время истечения токена
    public static final long EXPIRATION_TIME = 60_00_000; //100min
}
