package edu.neu.csye6220.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn
public class AirportStaff extends User {

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airport airport;

	@NotNull
	private String staffCode;

	public AirportStaff() {
	}

	public AirportStaff(String username, String password, String email, Role role, Airport airport, String staffCode) {
		super(username, password, email, role);
		this.airport = airport;
		this.staffCode = staffCode;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	
}
