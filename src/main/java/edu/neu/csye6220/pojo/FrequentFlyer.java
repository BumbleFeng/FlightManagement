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
public class FrequentFlyer {

	@Id
	@Column(name = "FrequentFlyer_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String frequentFlyerNumber;

	private int Mileage;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Airline airline;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Passenger passenger;

}
