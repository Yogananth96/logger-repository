package com.example.security.Entity;

public class TokenRequest {
    private String token;

    // Getter and Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	@Override
	public String toString() {
		return "TokenRequest [token=" + token + "]";
	}
    
}

