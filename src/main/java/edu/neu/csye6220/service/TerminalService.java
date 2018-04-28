package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AirportDAO;
import edu.neu.csye6220.dao.BaggageCarouselDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.GateDAO;
import edu.neu.csye6220.dao.TerminalDAO;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.BaggageCarousel;
import edu.neu.csye6220.pojo.Gate;
import edu.neu.csye6220.pojo.Terminal;

@Service
public class TerminalService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String airportCode, String terminalCode) {
		if(duplicate(airportCode, terminalCode)) return;
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport airport = airportDAO.get(airportCode);
		Terminal terminal = new Terminal(airport, terminalCode);
		TerminalDAO terminalDAO = daoFactory.createTerminalDAO();
		terminalDAO.add(terminal);
		Gate gate = new Gate("undefined", terminal);
		GateDAO gateDAO = daoFactory.createGateDAO();
		gateDAO.add(gate);
		BaggageCarousel baggageCarousel = new BaggageCarousel("undefined", terminal);
		BaggageCarouselDAO baggageCarouselDAO = daoFactory.createBaggageCarouselDAO();
		baggageCarouselDAO.add(baggageCarousel);
	}
	
	public boolean duplicate(String airportCode,String terminalCode) {
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport airport = airportDAO.get(airportCode);
		TerminalDAO terminalDAO = daoFactory.createTerminalDAO();
		Terminal terminal = terminalDAO.get(airport, terminalCode);
		if (terminal == null)
			return false;
		else
			return true;
	}
}
