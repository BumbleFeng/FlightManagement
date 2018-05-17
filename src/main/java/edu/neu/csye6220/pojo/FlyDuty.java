package edu.neu.csye6220.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.validation.constraints.Future;

import com.google.gson.annotations.Expose;

@Entity
public class FlyDuty {

	@Id
	@Column(name = "FlyDuty_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Flight flight;

	@Future
	@Expose
	private LocalDate date;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Terminal departureTerminal;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Terminal arrivalTerminal;

	private LocalDateTime boardingTime;

	@Expose
	private LocalDateTime actualDeparture;

	@Expose
	private LocalDateTime actualArrival;

	private String status;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Gate departureGate;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Gate arrivalGate;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private BaggageCarousel baggageCarousel;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Aircraft aircraft;

	private int firstclassRemain;

	private double firstclassPrice;

	private int businessRemain;

	private double businessPrice;

	private int economyRemain;

	private double economyPrice;
	
	private double sales;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flyDuty")
	private List<Ticket> tickets;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flyDuty")
	private List<Luggage> luggages;

	public FlyDuty() {
	}
	
	public FlyDuty(Flight flight, LocalDate date, Terminal departureTerminal, Terminal arrivalTerminal,
			LocalDateTime boardingTime, LocalDateTime actualDeparture, LocalDateTime actualArrival, Gate departureGate,
			Gate arrivalGate, BaggageCarousel baggageCarousel, Aircraft aircraft, int firstclassRemain,
			int businessRemain, int economyRemain) {
		this.flight = flight;
		this.date = date;
		this.departureTerminal = departureTerminal;
		this.arrivalTerminal = arrivalTerminal;
		this.boardingTime = boardingTime;
		this.actualDeparture = actualDeparture;
		this.actualArrival = actualArrival;
		this.departureGate = departureGate;
		this.arrivalGate = arrivalGate;
		this.baggageCarousel = baggageCarousel;
		this.aircraft = aircraft;
		this.firstclassRemain = firstclassRemain;
		this.businessRemain = businessRemain;
		this.economyRemain = economyRemain;
		this.status = "scheduled";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Terminal getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(Terminal departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public Terminal getArrivalTerminal() {
		return arrivalTerminal;
	}

	public void setArrivalTerminal(Terminal arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}

	public LocalDateTime getBoardingTime() {
		return boardingTime;
	}

	public void setBoardingTime(LocalDateTime boardingTime) {
		this.boardingTime = boardingTime;
	}

	public LocalDateTime getActualDeparture() {
		return actualDeparture;
	}

	public void setActualDeparture(LocalDateTime actualDeparture) {
		this.actualDeparture = actualDeparture;
	}

	public LocalDateTime getActualArrival() {
		return actualArrival;
	}

	public void setActualArrival(LocalDateTime actualArrival) {
		this.actualArrival = actualArrival;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Gate getDepartureGate() {
		return departureGate;
	}

	public void setDepartureGate(Gate departureGate) {
		this.departureGate = departureGate;
	}

	public Gate getArrivalGate() {
		return arrivalGate;
	}

	public void setArrivalGate(Gate arrivalGate) {
		this.arrivalGate = arrivalGate;
	}

	public BaggageCarousel getBaggageCarousel() {
		return baggageCarousel;
	}

	public void setBaggageCarousel(BaggageCarousel baggageCarousel) {
		this.baggageCarousel = baggageCarousel;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public int getFirstclassRemain() {
		return firstclassRemain;
	}

	public void setFirstclassRemain(int firstclassRemain) {
		this.firstclassRemain = firstclassRemain;
	}

	public double getFirstclassPrice() {
		return firstclassPrice;
	}

	public void setFirstclassPrice(double firstclassPrice) {
		this.firstclassPrice = firstclassPrice;
	}

	public int getBusinessRemain() {
		return businessRemain;
	}

	public void setBusinessRemain(int businessRemain) {
		this.businessRemain = businessRemain;
	}

	public double getBusinessPrice() {
		return businessPrice;
	}

	public void setBusinessPrice(double businessPrice) {
		this.businessPrice = businessPrice;
	}

	public int getEconomyRemain() {
		return economyRemain;
	}

	public void setEconomyRemain(int economyRemain) {
		this.economyRemain = economyRemain;
	}

	public double getEconomyPrice() {
		return economyPrice;
	}

	public void setEconomyPrice(double economyPrice) {
		this.economyPrice = economyPrice;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Luggage> getLuggages() {
		return luggages;
	}

	public void setLuggages(List<Luggage> luggages) {
		this.luggages = luggages;
	}

}
