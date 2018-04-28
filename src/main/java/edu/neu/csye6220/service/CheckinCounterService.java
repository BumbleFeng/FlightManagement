package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AirlineDAO;
import edu.neu.csye6220.dao.AirportDAO;
import edu.neu.csye6220.dao.CheckinCounterDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.TerminalDAO;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.CheckinCounter;
import edu.neu.csye6220.pojo.Terminal;

@Service
public class CheckinCounterService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String airlineCode, String airportCode, String terminalCode, String checkinCounterCode) {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport airport = airportDAO.get(airportCode);
		TerminalDAO terminalDAO = daoFactory.createTerminalDAO();
		Terminal terminal = terminalDAO.get(airport, terminalCode);
		CheckinCounter checkinCounter = new CheckinCounter(airline, terminal, checkinCounterCode);
		CheckinCounterDAO checkinCounterDAO = daoFactory.createCheckinDAO();
		checkinCounterDAO.add(checkinCounter);
	}

	public void add(Airline airline, Terminal terminal, String checkinCounterCode) {
		CheckinCounter checkinCounter = new CheckinCounter(airline, terminal, checkinCounterCode);
		CheckinCounterDAO checkinCounterDAO = daoFactory.createCheckinDAO();
		checkinCounterDAO.add(checkinCounter);
	}

	public List<CheckinCounter> list() {
		CheckinCounterDAO checkinCounterDAO = daoFactory.createCheckinDAO();
		return checkinCounterDAO.list();
	}
}
