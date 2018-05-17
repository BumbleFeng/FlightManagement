package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AirportDAO;
import edu.neu.csye6220.dao.AirportStaffDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.RoleDAO;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.AirportStaff;
import edu.neu.csye6220.pojo.Role;

@Service
public class AirportService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String airportCode, String airportName, String city, String country, String timeZone) {
		airportCode = airportCode.substring(0, 3).toUpperCase();
		if (duplicate(airportCode))
			return;
		String[] t = timeZone.split(":");
		int h = Integer.parseInt(t[0]);
		int m = Integer.parseInt(t[1]);
		int z = 0;
		if (h > 0) {
			z = h * 60 + m;
		} else {
			z = h * 60 - m;
		}
		Airport airport = new Airport(airportCode, airportName, city, country, z);
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		airportDAO.add(airport);
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(4);
		String username = airportCode + "admin";
		String email = "admin@" + airportCode.toLowerCase() + ".com";
		AirportStaff admin = new AirportStaff(username, "{noop}admin", email, role, airport, "admin");
		AirportStaffDAO airportStaffDAO = daoFactory.createAirportStaffDAO();
		airportStaffDAO.add(admin);
	}

	public List<Airport> list() {
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		return airportDAO.list();
	}

	public boolean duplicate(String airportCode) {
		airportCode = airportCode.substring(0, 3).toUpperCase();
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport airport = airportDAO.get(airportCode);
		if (airport == null)
			return false;
		else
			return true;
	}
}
