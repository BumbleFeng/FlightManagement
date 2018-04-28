package edu.neu.csye6220.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

@Entity
public class Ticket {

	@Id
	@Column(name = "Ticket_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	@Expose
	private FlyDuty flyDuty;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Itinerary itinerary;

	@NotNull
	@Expose
	private String seatClass;

	@NotNull
	@Expose
	private double price;

	public Ticket() {
	}

	public Ticket(FlyDuty flyDuty, Itinerary itinerary, String seatClass, double price) {
		this.flyDuty = flyDuty;
		this.itinerary = itinerary;
		this.seatClass = seatClass;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FlyDuty getFlyDuty() {
		return flyDuty;
	}

	public void setFlyDuty(FlyDuty flyDuty) {
		this.flyDuty = flyDuty;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
