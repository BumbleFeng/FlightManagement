package edu.neu.csye6220.pojo;

import java.time.LocalDateTime;
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
public class UserOrder{

	@Id
	@Column(name = "UserOrder_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private long id;
	
	@NotNull
	@Expose
	private LocalDateTime orderedTime;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userOrder", fetch = FetchType.EAGER)
	@Expose
	private List<Itinerary> itineraries;

	@NotNull
	@Expose
	private double price;

	public UserOrder() {
	}

	public UserOrder(LocalDateTime orderedTime, User user) {
		super();
		this.orderedTime = orderedTime;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public LocalDateTime getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(LocalDateTime orderedTime) {
		this.orderedTime = orderedTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
