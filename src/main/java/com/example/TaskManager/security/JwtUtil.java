package com.example.TaskManager.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;


@Component
public class JwtUtil {

    private final SecretKey secret  = Keys.hmacShaKeyFor("SanketSuperSecureJwtSecretKey1234".getBytes(StandardCharsets.UTF_8));


    public String generateToken(UserDetails userDetails) {

    System.out.println(">>> JWT SECRET:1 " + secret);

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim("role", userDetails.getAuthorities().iterator().next().getAuthority())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(secret, SignatureAlgorithm.HS256)
        .compact();
}


    public String extractUsername(String token) {
        System.out.println(">>> JWT SECRET: " + secret);
System.out.println(">>> JWT TOKEN: " + token);
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
