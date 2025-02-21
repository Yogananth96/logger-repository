package com.example.security.Dto;

public class AuthResponse {
	private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

	@Override
	public String toString() {
		return "AuthResponse [token=" + token + "]";
	}
    
}
