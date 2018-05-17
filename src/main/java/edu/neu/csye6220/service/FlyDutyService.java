package edu.neu.csye6220.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AircraftDAO;
import edu.neu.csye6220.dao.AirlineDAO;
import edu.neu.csye6220.dao.BaggageCarouselDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.FlightDAO;
import edu.neu.csye6220.dao.FlyDutyDAO;
import edu.neu.csye6220.dao.GateDAO;
import edu.neu.csye6220.dao.TerminalDAO;
import edu.neu.csye6220.dao.TicketDAO;
import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.BaggageCarousel;
import edu.neu.csye6220.pojo.Flight;
import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.pojo.Gate;
import edu.neu.csye6220.pojo.Terminal;
import edu.neu.csye6220.pojo.Ticket;

@Service
public class FlyDutyService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String callSign, String date, String departureTerminalCode, String arrivalTerminalCode,
			String aircraftCode) {
		FlightDAO flightDAO = daoFactory.createFlightDAO();
		Flight flight = flightDAO.get(callSign);
		LocalDate localDate = LocalDate.parse(date);
		Airport departureAirport = flightDAO.departure(callSign);
		Airport arrivalAirport = flightDAO.arrival(callSign);
		TerminalDAO terminalDAO = daoFactory.createTerminalDAO();
		Terminal departureTerminal = terminalDAO.get(departureAirport, departureTerminalCode);
		Terminal arrivalTerminal = terminalDAO.get(arrivalAirport, arrivalTerminalCode);
		LocalTime departureTime = flight.getScheduledDeparture();
		LocalDateTime actualDeparture = departureTime.atDate(localDate);
		LocalDateTime boardingTime = actualDeparture.minusMinutes(30);
		int arrival = flight.getScheduledArrival();
		LocalDateTime actualArrival = actualDeparture.plusMinutes(arrival);
		AircraftDAO aircraftDAO = daoFactory.createAircraftDAO();
		Aircraft aircraft = aircraftDAO.get(aircraftCode);
		GateDAO gateDAO = daoFactory.createGateDAO();
		Gate departureGate = gateDAO.undefined(departureTerminal);
		Gate arrivalGate = gateDAO.undefined(arrivalTerminal);
		BaggageCarouselDAO baggageCarouselDAO = daoFactory.createBaggageCarouselDAO();
		BaggageCarousel baggageCarousel = baggageCarouselDAO.undefined(arrivalTerminal);
		FlyDuty flyDuty = new FlyDuty(flight, localDate, departureTerminal, arrivalTerminal, boardingTime,
				actualDeparture, actualArrival, departureGate, arrivalGate, baggageCarousel, aircraft,
				aircraft.getFirstclassSeats(), aircraft.getBusinessSeats(), aircraft.getEconomicSeats());
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		flyDutyDAO.add(flyDuty);
	}

	public FlyDuty get(String callSign, String date) {
		FlightDAO flightDAO = daoFactory.createFlightDAO();
		Flight flight = flightDAO.get(callSign);
		LocalDate localDate = LocalDate.parse(date);
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		FlyDuty flyDuty = flyDutyDAO.get(flight, localDate);
		return flyDuty;
	}

	public FlyDuty getById(String id) {
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		Long i = Long.valueOf(id);
		FlyDuty flyDuty = flyDutyDAO.getById(i);
		return flyDuty;
	}

	public List<FlyDuty> list(String airlineCode) {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		return flyDutyDAO.list(airline);
	}

	public void update(FlyDuty flyDuty) {
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		flyDutyDAO.update(flyDuty);
	}

	public List<FlyDuty> search(String departureCity, String arrivalCity, String date) {
		FlyDutyDAO flyDutyDAO = daoFactory.createFlyDutyDAO();
		LocalDate localDate = LocalDate.parse(date);
		return flyDutyDAO.search(departureCity, arrivalCity, localDate);
	}

	public void updateOrder(FlyDuty flyDuty) {
		TicketDAO ticketDAO = daoFactory.createTicketDAO();
		List<Ticket> tickets = ticketDAO.list(flyDuty);
		int F = 0, B = 0, E = 0;
		double sales = 0;
		for (Ticket ticket : tickets) {
			if (ticket.getSeatClass().equals("F")) F++;
			if (ticket.getSeatClass().equals("B")) B++;
			if (ticket.getSeatClass().equals("E")) E++;
			sales += ticket.getPrice();
		}
		flyDuty.setSales(sales);
		Aircraft aircraft = flyDuty.getAircraft();
		flyDuty.setFirstclassRemain(aircraft.getFirstclassSeats() - F);
		flyDuty.setBusinessRemain(aircraft.getBusinessSeats() - B);
		flyDuty.setEconomyRemain(aircraft.getEconomicSeats() - E);
		update(flyDuty);
	}
}
