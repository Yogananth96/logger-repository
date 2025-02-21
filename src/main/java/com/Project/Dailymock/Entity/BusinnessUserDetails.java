package com.Project.Dailymock.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BusinnessUserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	
	private String email;

	@Column(nullable = false)
	private Long mobile;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonManagedReference
	
	List<BusinessDetails> business =new ArrayList<>();

	public BusinnessUserDetails() {

	}

	public BusinnessUserDetails(Integer id, String name, String email, Long mobile, String password, String role,
			List<BusinessDetails> business) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
		this.business = business;
	}

	public BusinnessUserDetails(Integer id, String name, String email, Long mobile, String password, String role) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<BusinessDetails> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessDetails> business) {
		this.business = business;
		
	}

	@Override
	public String toString() {
		return "BusinnessUserDetails [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + ", role=" + role + ", business=" + business + "]";
	}

}
