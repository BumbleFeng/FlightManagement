package edu.neu.csye6220.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class BoardingPass {

	@Id
	@Column(name = "BoardingPass_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Itinerary itinerary;

	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Seat seat;
}
