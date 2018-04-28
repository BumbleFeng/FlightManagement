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
public class Terminal {
	
	@Id
	@Column(name = "Terminal_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airport airport;
	
	@NotNull
	@Expose
	private String terminalCode;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "terminal")
	private List<CheckinCounter> checkinCounters;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "terminal")
	private List<Gate> gates;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "terminal")
	private List<BaggageCarousel> baggageCarousels;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departureTerminal")
	private List<FlyDuty> departureFlys;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalTerminal")
	private List<FlyDuty> arrivalFlys;
	
	public Terminal() {
	}
	
	public Terminal(Airport airport, String terminalCode) {
		this.airport = airport;
		this.terminalCode = terminalCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public List<CheckinCounter> getCheckins() {
		return checkinCounters;
	}

	public void setCheckinCounters(List<CheckinCounter> checkinCounters) {
		this.checkinCounters = checkinCounters;
	}

	public List<Gate> getGates() {
		return gates;
	}

	public void setGates(List<Gate> gates) {
		this.gates = gates;
	}

	public List<BaggageCarousel> getBaggageCarousels() {
		return baggageCarousels;
	}

	public void setBaggageCarousels(List<BaggageCarousel> baggageCarousels) {
		this.baggageCarousels = baggageCarousels;
	}
	
	public List<FlyDuty> getDepartureFlys() {
		return departureFlys;
	}

	public void setDepartureFlys(List<FlyDuty> departureFlys) {
		this.departureFlys = departureFlys;
	}

	public List<FlyDuty> getArrivalFlys() {
		return arrivalFlys;
	}

	public void setArrivalFlys(List<FlyDuty> arrivalFlys) {
		this.arrivalFlys = arrivalFlys;
	}
}
