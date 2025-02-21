package com.Project.Dailymock.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private LocalDateTime inTime = LocalDateTime.now();

	private LocalDateTime outTime = LocalDateTime.now();

	public Attendance() {

	}

	public Attendance(Long id, Employee employee, String status, LocalDateTime inTime, LocalDateTime outTime) {

		this.id = id;
		this.employee = employee;
		this.status = status;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalDateTime inTime) {
		this.inTime = inTime;
	}

	public LocalDateTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", employee=" + employee + ", status=" + status + ", inTime=" + inTime
				+ ", outTime=" + outTime + "]";
	}

}
