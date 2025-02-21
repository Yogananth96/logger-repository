package com.Project.Dailymock.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BusinessDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="businessname")
	private String businessname;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String sector;

	@Column(nullable = false,name="subsector")
	private String subsector;

	@Column(nullable = false,name="noofemployees")
	private Integer noofemployees;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "businessuserdetails_id",referencedColumnName = "id",nullable=false)
	@JsonBackReference
	private BusinnessUserDetails user=new BusinnessUserDetails();

	public BusinessDetails() {

	}

	

	



	public BusinessDetails(Integer id, String businessname, String state, String city, String sector, String subsector,
			Integer noofemployees, BusinnessUserDetails user) {
	
		this.id = id;
		this.businessname = businessname;
		this.state = state;
		this.city = city;
		this.sector = sector;
		this.subsector = subsector;
		this.noofemployees = noofemployees;
		this.user = user;
	}







	public Integer getId() {
		return id;
	}







	public void setId(Integer id) {
		this.id = id;
	}







	public String getBusinessname() {
		return businessname;
	}







	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}







	public String getState() {
		return state;
	}







	public void setState(String state) {
		this.state = state;
	}







	public String getCity() {
		return city;
	}







	public void setCity(String city) {
		this.city = city;
	}







	public String getSector() {
		return sector;
	}







	public void setSector(String sector) {
		this.sector = sector;
	}







	public String getSubsector() {
		return subsector;
	}







	public void setSubsector(String subsector) {
		this.subsector = subsector;
	}







	public Integer getNoofemployees() {
		return noofemployees;
	}







	public void setNoofemployees(Integer noofemployees) {
		this.noofemployees = noofemployees;
	}







	public BusinessDetails(Integer id, String businessname, String state, String city, String sector, String subsector,
			Integer noofemployees) {
		
		this.id = id;
		this.businessname = businessname;
		this.state = state;
		this.city = city;
		this.sector = sector;
		this.subsector = subsector;
		this.noofemployees = noofemployees;
	}







	public BusinnessUserDetails getUser() {
		return user;
	}







	public void setUser(BusinnessUserDetails user) {
		this.user = user;
	}







	@Override
	public String toString() {
		return "BusinessDetails [id=" + id + ", businessname=" + businessname + ", state=" + state + ", city=" + city
				+ ", sector=" + sector + ", subsector=" + subsector + ", noofemployees=" + noofemployees + ", user="
				+ user + "]";
	}







	

}
