package com.Project.Dailymock.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeid;

	@Column(nullable = false)
	private String employeeName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String mobile;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String designation;

	@Column(nullable = false)
	private String department;

	@Column(nullable = false)
	private LocalDate joinDate;

	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attendance> attendances = new ArrayList<>();

	// Constructors
	public Employee() {
	}

	public Employee(Long employeeid, String employeeName, String email, String password, String mobile, String gender,
			String designation, String department, LocalDate joinDate, byte[] image, List<Attendance> attendances) {
		this.employeeid = employeeid;
		this.employeeName = employeeName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.gender = gender;
		this.designation = designation;
		this.department = department;
		this.joinDate = joinDate;
		this.image = image;
		this.attendances = attendances;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Long getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeeName=" + employeeName + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", gender=" + gender + ", designation="
				+ designation + ", department=" + department + ", joinDate=" + joinDate + ", image="
				+ Arrays.toString(image) + ", attendances=" + attendances + "]";
	}

	}
