package edu.neu.csye6220.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
public class Airline {

	@Id
	@Column(name = "Airline_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Expose
	private String airlineCode;

	@NotNull
	@Expose
	private String airlineName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private List<FrequentFlyer> frequentFlyers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private List<AirlineStaff> staffs;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private List<Flight> flights;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private List<Aircraft> aircrafts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private List<CheckinCounter> checkinCounters;
	
	public Airline() {
	}
	
	public Airline(String airlineCode, String airlineName) {
		this.airlineCode = airlineCode;
		this.airlineName = airlineName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public List<FrequentFlyer> getFrequentFlyers() {
		return frequentFlyers;
	}

	public void setFrequentFlyers(List<FrequentFlyer> frequentFlyers) {
		this.frequentFlyers = frequentFlyers;
	}

	public List<AirlineStaff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<AirlineStaff> staffs) {
		this.staffs = staffs;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Aircraft> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(List<Aircraft> aircrafts) {
		this.aircrafts = aircrafts;
	}

	public List<CheckinCounter> getCheckinCounters() {
		return checkinCounters;
	}

	public void setCheckinCounters(List<CheckinCounter> checkinCounters) {
		this.checkinCounters = checkinCounters;
	}
	
}
