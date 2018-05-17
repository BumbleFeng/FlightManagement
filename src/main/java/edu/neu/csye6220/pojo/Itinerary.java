package edu.neu.csye6220.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
public class Itinerary {
	@Id
	@Column(name = "Itinerary_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private Passenger passenger;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerary", fetch = FetchType.EAGER)
	@Expose
	private List<Ticket> tickets;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerary")
	private List<BoardingPass> boardingPasses;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerary")
	private List<Luggage> luggages;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private UserOrder userOrder;

	@NotNull
	@Expose
	private double price;

	public Itinerary() {
	}

	public Itinerary(Passenger passenger, UserOrder userOrder) {
		this.passenger = passenger;
		this.userOrder = userOrder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<BoardingPass> getBoardingPasses() {
		return boardingPasses;
	}

	public void setBoardingPasses(List<BoardingPass> boardingPasses) {
		this.boardingPasses = boardingPasses;
	}

	public List<Luggage> getLuggages() {
		return luggages;
	}

	public void setLuggages(List<Luggage> luggages) {
		this.luggages = luggages;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
