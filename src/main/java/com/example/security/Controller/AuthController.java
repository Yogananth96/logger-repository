package com.example.security.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.Dto.AuthResponse;
import com.example.security.Dto.LoginRequest;
import com.example.security.Dto.RegisterRequest;
import com.example.security.Entity.Role;
import com.example.security.Entity.User;
import com.example.security.Repository.RoleRepository;
import com.example.security.Repository.UserRepository;
import com.example.security.Security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// ✅ Register User API
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

		// Check if user already exists
		if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Username already exists");
		}

		// Create new user
		User newUser = new User();
		newUser.setUsername(registerRequest.getUsername());
		newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		// Assign roles to user
		Set<Role> roles = new HashSet<>();    // using set for not allow duplicate values.
		//for each loop 
		for (String roleName : registerRequest.getRoles()) {
			Role role = roleRepository.findByName(roleName).orElseGet(() -> {
				Role newRole = new Role();
				newRole.setName(roleName);
				return roleRepository.save(newRole); // Save role if not found
			});
			roles.add(role);
		}
		newUser.setRoles(roles);
		userRepository.save(newUser);

		return ResponseEntity.ok("User registered successfully");
	}

	// ✅ Login API
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {
//			Authentication authentication1 = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			authenticationManager.authenticate(authentication);
			// Set authentication context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Generate JWT token
			String token = jwtUtil.generateToken(loginRequest.getUsername());

			return ResponseEntity.ok(new AuthResponse(token));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid username or password");
		}
	}
}
