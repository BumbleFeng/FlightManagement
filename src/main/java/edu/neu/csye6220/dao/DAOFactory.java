package edu.neu.csye6220.dao;

import org.springframework.stereotype.Repository;

@Repository
public class DAOFactory {

	public AircraftDAO createAircraftDAO() {
		return new AircraftDAO();
	}

	public AirlineDAO createAirlineDAO() {
		return new AirlineDAO();
	}

	public AirlineStaffDAO createAirlineStaffDAO() {
		return new AirlineStaffDAO();
	}
	
	public AirportDAO createAirportDAO() {
		return new AirportDAO();
	}
	
	public AirportStaffDAO createAirportStaffDAO() {
		return new AirportStaffDAO();
	}
	
	public BaggageCarouselDAO createBaggageCarouselDAO() {
		return new BaggageCarouselDAO();
	}

	public CheckinCounterDAO createCheckinDAO() {
		return new CheckinCounterDAO();
	}
	
	public FlightDAO createFlightDAO() {
		return new FlightDAO();
	}
	
	public FlyDutyDAO createFlyDutyDAO() {
		return new FlyDutyDAO();
	}
	
	public GateDAO createGateDAO() {
		return new GateDAO();
	}
	
	public ItineraryDAO createItineraryDAO() {
		return new ItineraryDAO();
	}
	
	public PassengerDAO createPassengerDAO() {
		return new PassengerDAO();
	}
	
	public RoleDAO createRoleDAO() {
		return new RoleDAO();
	}
	
	public SeatDAO createSeatDAO() {
		return new SeatDAO();
	}

	public TerminalDAO createTerminalDAO() {
		return new TerminalDAO();
	}
	
	public TicketDAO createTicketDAO() {
		return new TicketDAO();
	}

	public UserDAO createUserDAO() {
		return new UserDAO();
	}
	
	public UserOrderDAO createUserOrderDAO() {
		return new UserOrderDAO();
	}
}
