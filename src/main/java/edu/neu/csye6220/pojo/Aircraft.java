package edu.neu.csye6220.pojo;

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
public class Aircraft {

	@Id
	@Column(name = "Aircraft_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airline airline;

	@NotNull
	@Expose
	private String aircraftCode;

	@NotNull
	@Expose
	private String model;

	@NotNull
	private int age;

	@NotNull
	private int firstclassSeats;

	@NotNull
	private int businessSeats;

	@NotNull
	private int economicSeats;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft")
	private List<FlyDuty> flyDuties;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aircraft")
	private List<Seat> seats;
	
	
	public Aircraft() {
	}

	public Aircraft(Airline airline, String aircraftCode, String model, int age, int firstclassSeats, int businessSeats,
			int economicSeats) {
		this.airline = airline;
		this.aircraftCode = aircraftCode;
		this.model = model;
		this.age = age;
		this.firstclassSeats = firstclassSeats;
		this.businessSeats = businessSeats;
		this.economicSeats = economicSeats;
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

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFirstclassSeats() {
		return firstclassSeats;
	}

	public void setFirstclassSeats(int firstclassSeats) {
		this.firstclassSeats = firstclassSeats;
	}

	public int getBusinessSeats() {
		return businessSeats;
	}

	public void setBusinessSeats(int businessSeats) {
		this.businessSeats = businessSeats;
	}

	public int getEconomicSeats() {
		return economicSeats;
	}

	public void setEconomicSeats(int economicSeats) {
		this.economicSeats = economicSeats;
	}

	public List<FlyDuty> getFlyDuties() {
		return flyDuties;
	}

	public void setFlyDuties(List<FlyDuty> flyDuties) {
		this.flyDuties = flyDuties;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
