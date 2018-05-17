package edu.neu.csye6220.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Seat {

	@Id
	@Column(name = "Seat_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Aircraft aircraft;
	
	private String seatClass;
	
	private String seatCode;

	private String status;

	private int boardingGroup;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "seat")
	private BoardingPass boardingPass;

	public Seat() {
	}

	public Seat(Aircraft aircraft, String seatClass, String seatCode, int boardingGroup) {
		this.aircraft = aircraft;
		this.seatClass = seatClass;
		this.seatCode = seatCode;
		this.boardingGroup = boardingGroup;
		this.status = "R";//Remain
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBoardingGroup() {
		return boardingGroup;
	}

	public void setBoardingGroup(int boardingGroup) {
		this.boardingGroup = boardingGroup;
	}

	public BoardingPass getBoardingPass() {
		return boardingPass;
	}

	public void setBoardingPass(BoardingPass boardingPass) {
		this.boardingPass = boardingPass;
	}
	
}
