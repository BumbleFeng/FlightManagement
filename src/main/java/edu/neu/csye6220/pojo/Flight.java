package edu.neu.csye6220.pojo;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
public class Flight {

	@Id
	@Column(name = "Flight_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airline airline;

	@NotNull
	@Expose
	private String callSign;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Airport departureAirport;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Airport arrivalAirport;

	@NotNull
	private LocalTime scheduledDeparture;

	@NotNull
	private int scheduledArrival;

	@NotNull
	private LocalTime flightHour;

	@NotNull
	private int mileage;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
	private List<FlyDuty> flyDuties;

	public Flight() {
	}

	public Flight(Airline airline, String callSign, Airport departureAirport, Airport arrivalAirport,
			LocalTime scheduledDeparture, int scheduledArrival, LocalTime flightHour, int mileage) {
		this.airline = airline;
		this.callSign = callSign;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.scheduledDeparture = scheduledDeparture;
		this.scheduledArrival = scheduledArrival;
		this.flightHour = flightHour;
		this.mileage = mileage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getCallSign() {
		return callSign;
	}

	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalTime getScheduledDeparture() {
		return scheduledDeparture;
	}

	public void setScheduledDeparture(LocalTime scheduledDeparture) {
		this.scheduledDeparture = scheduledDeparture;
	}

	public int getScheduledArrival() {
		return scheduledArrival;
	}

	public void setScheduledArrival(int scheduledArrival) {
		this.scheduledArrival = scheduledArrival;
	}

	public LocalTime getFlightHour() {
		return flightHour;
	}

	public void setFlightHour(LocalTime flightHour) {
		this.flightHour = flightHour;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public List<FlyDuty> getFlyDuties() {
		return flyDuties;
	}

	public void setFlyDuties(List<FlyDuty> flyDuties) {
		this.flyDuties = flyDuties;
	}
	
}
