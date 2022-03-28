package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {

    @Autowired
    private UserService userService;

    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication) {
        User userMid = (User) authentication.getPrincipal(); // object "principal" stores inside it user's information

        User user = userService.getUserByEmail(userMid.getEmail());

        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME); // time when token ends

        String userId = Long.toString(user.getId());

        Map<String, Object> claimsMap = new HashMap<>(); // making map with user information
        claimsMap.put("id", userId);
        claimsMap.put("email", user.getEmail());
        claimsMap.put("firstName", user.getFirstName());
        claimsMap.put("lastName", user.getLastName());

        return Jwts.builder()
                .setSubject(userId)
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();

    }

    public boolean validateToken(String token) {
        // if token is ok return true? if some error occured return false

        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        // get id from map, that we made before in generateToken(Authentication authentication)
        // we decode token and takes data(id) from it
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
