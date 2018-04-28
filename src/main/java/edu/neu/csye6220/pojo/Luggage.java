package edu.neu.csye6220.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Luggage {

	@Id
	@Column(name = "Luggage_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double weight;

	private String status;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Itinerary itinerary;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private FlyDuty flyDuty;
	
	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private BaggageCarousel baggageCarousel;
}
