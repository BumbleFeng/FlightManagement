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
public class Gate {

	@Id
	@Column(name = "Gate_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String GateCode;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Terminal terminal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departureGate")
	private List<FlyDuty> departureFlys;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalGate")
	private List<FlyDuty> arrivalFlys;

	public Gate() {
	}

	public Gate(String gateCode, Terminal terminal) {
		GateCode = gateCode;
		this.terminal = terminal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGateCode() {
		return GateCode;
	}

	public void setGateCode(String gateCode) {
		GateCode = gateCode;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
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
