package com.company.training.modules.system.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private static final SecretKey KEY = Keys.hmacShaKeyFor("it-training-system-jwt-secret-key-2026-strong".getBytes());

    public String createToken(Long userId, String username, List<String> perms) {
        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("perms", perms)
                .expiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000))
                .signWith(KEY)
                .compact();
    }

    public boolean validate(String token) {
        try { Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token); return true; } catch (Exception e) { return false; }
    }
}
