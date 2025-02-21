package com.example.security.Service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
// UserDetailsService - read only method - loads user-specific data.
    private final UserRepository userRepository;

    // Constructor-based dependency injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//UserDetails interface extends Serializable - provides core user information.
    	//throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority.
        // Find user in the database
        com.example.security.Entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Map roles to Spring Security authorities
        // private final string role;
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())) //SimpleGrantedAuthority - final class 
                        .collect(Collectors.toSet()) // Using Set to avoid duplicates
        );
    }
}

