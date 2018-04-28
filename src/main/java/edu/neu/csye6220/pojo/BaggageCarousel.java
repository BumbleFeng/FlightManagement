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

@Entity
public class BaggageCarousel {

	@Id
	@Column(name = "BaggageCarousel_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String baggageCarouselCode;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Terminal terminal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "baggageCarousel")
	private List<FlyDuty> flyDuties;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "baggageCarousel")
	private List<Luggage> luggages;

	public BaggageCarousel() {
	}

	public BaggageCarousel(String baggageCarouselCode, Terminal terminal) {
		this.baggageCarouselCode = baggageCarouselCode;
		this.terminal = terminal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBaggageCarouselCode() {
		return baggageCarouselCode;
	}

	public void setBaggageCarouselCode(String baggageCarouselCode) {
		this.baggageCarouselCode = baggageCarouselCode;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public List<FlyDuty> getFlyDuties() {
		return flyDuties;
	}

	public void setFlyDuties(List<FlyDuty> flyDuties) {
		this.flyDuties = flyDuties;
	}

	public List<Luggage> getLuggages() {
		return luggages;
	}

	public void setLuggages(List<Luggage> luggages) {
		this.luggages = luggages;
	}
	
}
