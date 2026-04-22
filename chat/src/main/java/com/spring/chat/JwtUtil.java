package com.spring.chat;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    //private final String SECRET = "mySecretKey";
    private final String SECRET = "myverysecuresecretkeythatisatleast32chars"; //added this right now
    private final long EXPIRATION = 86400000; // 1 day

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getKey()).compact();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token){
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
