package edu.neu.csye6220.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn
public class AirlineStaff extends User {

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airline airline;

	@NotNull
	private String staffCode;

	public AirlineStaff() {
	}

	public AirlineStaff(String username, String password, String email, Role role, Airline airline, String staffCode) {
		super(username, password, email, role);
		this.airline = airline;
		this.staffCode = staffCode;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	
}
