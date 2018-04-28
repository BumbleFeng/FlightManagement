package edu.neu.csye6220.pojo;

import java.time.LocalDate;
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
public class Passenger {

	@Id
	@Column(name = "Passenger_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String realId;

	@NotNull
	@Expose
	private String firstName;

	@NotNull
	@Expose
	private String lastName;

	@NotNull
	private LocalDate birthday;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
	private List<FrequentFlyer> frequentFlyers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
	private List<Itinerary> itineraries;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private User user;

	public Passenger() {
	}

	public Passenger(String realId, String firstName, String lastName, LocalDate birthday, User user) {
		super();
		this.realId = realId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public List<FrequentFlyer> getFrequentFlyers() {
		return frequentFlyers;
	}

	public void setFrequentFlyers(List<FrequentFlyer> frequentFlyers) {
		this.frequentFlyers = frequentFlyers;
	}

	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
