package edu.neu.csye6220.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AirlineDAO;
import edu.neu.csye6220.dao.AirportDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.FlightDAO;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.Flight;

@Service
public class FlightService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String callSign, String departureAirportCode, String arrivalAirportCode, String scheduledDeparture,
			String flightHour, int mileage) {
		callSign = callSign.toUpperCase();
		if(duplicate(callSign)) return;
		String airlineCode = callSign.substring(0, 2);
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport departureAirport = airportDAO.get(departureAirportCode);
		Airport arrivalAirport = airportDAO.get(arrivalAirportCode);
		int dz = departureAirport.getTimeZone();
		int az = arrivalAirport.getTimeZone();
		LocalTime d = LocalTime.parse(scheduledDeparture);
		LocalTime f = LocalTime.parse(flightHour);
		int a = f.getHour() * 60 + f.getMinute() - dz + az;
		Flight flight = new Flight(airline, callSign, departureAirport, arrivalAirport, d, a, f, mileage);
		FlightDAO flightDAO = daoFactory.createFlightDAO();
		flightDAO.add(flight);
	}
	
	public List<Flight> list(String airlineCode) {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		FlightDAO flightDAO = daoFactory.createFlightDAO();
		return flightDAO.list(airline);
	}

	public boolean duplicate(String callSign) {
		callSign = callSign.toUpperCase();
		FlightDAO flightDAO = daoFactory.createFlightDAO();
		Flight flight = flightDAO.get(callSign);
		if (flight == null)
			return false;
		else
			return true;
	}

}
