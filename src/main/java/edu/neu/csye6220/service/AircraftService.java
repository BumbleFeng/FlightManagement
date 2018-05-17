package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AircraftDAO;
import edu.neu.csye6220.dao.AirlineDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.SeatDAO;
import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Seat;

@Service
public class AircraftService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String airlineCode, String aircraftCode, String model, int age, int firstclassSeats,
			int businessSeats, int economicSeats) {
		aircraftCode = aircraftCode.toUpperCase();
		if (duplicate(aircraftCode))
			return;
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		Aircraft aircraft = new Aircraft(airline, aircraftCode, model, age, firstclassSeats, businessSeats,
				economicSeats);
		AircraftDAO aircraftDAO = daoFactory.createAircraftDAO();
		aircraftDAO.add(aircraft);
		SeatDAO seatDAO = daoFactory.createSeatDAO();
		for (int i = 0; i < firstclassSeats; i++) {
			Character seatCode = (char)(i+65);
			Seat seat = new Seat(aircraft, "F", seatCode.toString(), 1);
			seatDAO.add(seat);
		}
		for (int i = 0; i < businessSeats; i++) {
			Character seatCode = (char)(i+65);
			Seat seat = new Seat(aircraft, "B", seatCode.toString(), 2);
			seatDAO.add(seat);
		}
		for (int i = 0; i < economicSeats; i++) {
			Character seatCode = (char)(i+65);
			Seat seat = new Seat(aircraft, "E", seatCode.toString(), 3);
			seatDAO.add(seat);
		}
	}

	public List<Aircraft> list(String airlineCode) {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		AircraftDAO aircraftDAO = daoFactory.createAircraftDAO();
		return aircraftDAO.list(airline);
	}

	public boolean duplicate(String aircraftCode) {
		aircraftCode = aircraftCode.toUpperCase();
		AircraftDAO aircraftDAO = daoFactory.createAircraftDAO();
		Aircraft aircraft = aircraftDAO.get(aircraftCode);
		if (aircraft == null)
			return false;
		else
			return true;
	}
}
