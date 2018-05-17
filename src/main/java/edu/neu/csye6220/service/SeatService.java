package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AircraftDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.SeatDAO;
import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.Seat;

@Service
public class SeatService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String aircraftCode, String seatClass, String seatCode, int boardingGroup) {
		AircraftDAO aircraftDAO = daoFactory.createAircraftDAO();
		Aircraft aircraft = aircraftDAO.get(aircraftCode);
		Seat seat = new Seat(aircraft, seatClass, seatCode, boardingGroup);
		SeatDAO seatDAO = daoFactory.createSeatDAO();
		seatDAO.add(seat);
	}
}
