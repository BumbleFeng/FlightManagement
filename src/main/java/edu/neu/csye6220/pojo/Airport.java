package edu.neu.csye6220.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
public class Airport {

	@Id
	@Column(name = "Airport_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Expose
	private String airportCode;

	@NotNull
	private String airportName;

	@NotNull
	@Expose
	private String city;

	@NotNull
	private String country;

	@NotNull
	private int timeZone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departureAirport")
	private List<Flight> departureFlights;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirport")
	private List<Flight> arrivalFlights;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airport", fetch = FetchType.EAGER)
	@Expose
	private List<Terminal> terminals;

	public Airport() {
	}

	public Airport(String airportCode, String airportName, String city, String country, int timeZone) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.city = city;
		this.country = country;
		this.timeZone = timeZone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}

	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}

	public List<Terminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<Terminal> terminals) {
		this.terminals = terminals;
	}

}
