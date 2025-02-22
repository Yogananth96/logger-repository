package com.example.security.Dto;

import java.util.Set;

public class RegisterRequest {
	
	private String username;
	private String password;
	private Set<String> roles;// set of roles name passed in the request
	public RegisterRequest(String username, String password, Set<String> roles) {
		
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public RegisterRequest() {
	
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "RegisterRequest [username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
	
	

}

