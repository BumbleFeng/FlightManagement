package edu.neu.csye6220.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class CheckinCounter {

	@Id
	@Column(name = "CheckinCounter_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airline airline;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Terminal terminal;

	@NotNull
	private String checkinCounterCode;
	
	public CheckinCounter() {
	}
	
	public CheckinCounter(Airline airline, Terminal terminal, String checkinCounterCode) {
		this.airline = airline;
		this.terminal = terminal;
		this.checkinCounterCode = checkinCounterCode;
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
	
	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public String getCheckinCounterCode() {
		return checkinCounterCode;
	}

	public void setCheckinCounterCode(String checkinCounterCode) {
		this.checkinCounterCode = checkinCounterCode;
	}

}
