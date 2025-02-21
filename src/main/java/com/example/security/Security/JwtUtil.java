package com.example.security.Security;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.security.Entity.Role;
import com.example.security.Entity.User;
import com.example.security.Repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

@Component
public class JwtUtil {

    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS512;
    private static final SecretKey SECRET_KEY = ALGORITHM.key().build();

    private static final long JWT_EXPIRATION_MS = 86400000L; // 1 day in milliseconds

    private final UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Generate JWT Token
    public String generateToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return Jwts.builder()    //JWT body
                .subject(username) // set username
                .claim("roles", String.join(",", roles)) // Stores user roles in the token as comma-separated values
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(SECRET_KEY)
                .compact();    //Builds and returns the JWT as a string
    }

    // Extract Username from Token
    public String extractUsername(String token) {
        return parseTokenClaims(token).getSubject();
    }

    // Extract Roles from Token
    public Set<String> extractRoles(String token) {
        String rolesString = parseTokenClaims(token).get("roles", String.class);
        return Set.of(rolesString.split(",")); // Convert to a Set
    }

    // Validate Token
    public boolean isTokenValid(String token) {
        try {
            parseTokenClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ FIXED: Correct `parseTokenClaims()` Method
    private Claims parseTokenClaims(String token) {
        return Jwts.parser()   //decode, validate and extract claims 
                   .verifyWith(SECRET_KEY)  // New validation method in JJWT 12.6 //Validates the token signature using SECRET_KEY// throws SignatureException
                   .build()
                   .parseSignedClaims(token) // throws JWTException and IllegalArgumentException
                   .getPayload(); // Extract Claims (Body)
    }
}
