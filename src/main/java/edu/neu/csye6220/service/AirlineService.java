package edu.neu.csye6220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.AirlineDAO;
import edu.neu.csye6220.dao.AirlineStaffDAO;
import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.RoleDAO;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.AirlineStaff;
import edu.neu.csye6220.pojo.Role;

@Service
public class AirlineService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String airlineCode, String airlineName) {
		airlineCode = airlineCode.substring(0, 2).toUpperCase();
		if (duplicate(airlineCode))
			return;
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = new Airline(airlineCode, airlineName);
		airlineDAO.add(airline);
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(6);
		String username = airlineCode + "admin";
		String email = "admin@" + airlineCode.toLowerCase() + ".com";
		AirlineStaff admin = new AirlineStaff(username, "{noop}admin", email, role, airline, "admin");
		AirlineStaffDAO airlineStaffDAO = daoFactory.createAirlineStaffDAO();
		airlineStaffDAO.add(admin);
	}

	public List<Airline> list() {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		return airlineDAO.list();
	}

	public boolean duplicate(String airlineCode) {
		airlineCode = airlineCode.substring(0, 2).toUpperCase();
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		if (airline == null)
			return false;
		else
			return true;
	}
}
