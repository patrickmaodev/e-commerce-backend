package com.ecommerce.e_commerce.auth.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.time}")
    private long expirationTime;

    // Create a Key from the secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Generate JWT Token using email
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Use email as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()) // Explicitly specify HS256 algorithm
                .compact();
    }

    // Extract email (subject) from JWT Token
    public String extractEmail(String token) {
        Claims claims = Jwts.parser() // Use parser() to get JwtParser
                .setSigningKey(getSigningKey()) // Use the signing key
                .parseClaimsJws(token) // Parse the JWT
                .getBody();
        return claims.getSubject(); // Subject will now hold the email
    }

    // Validate Token
    public boolean validateToken(String token, String email) {
        String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts.parser() // Use parser() to get JwtParser
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token) // Parse the JWT
                .getBody();
        return claims.getExpiration(); // Return the expiration date
    }
}
