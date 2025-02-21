package com.example.security.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.security.Security.JwtUtil;

@Service
public class SecurityCheckService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityCheckService(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // ✅ Get Logged-in User Details
    public UserDetails getUserDetails(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    // ✅ Validate JWT Token
    public boolean validateToken(String token) {
        return jwtUtil.isTokenValid(token);
    }
}

