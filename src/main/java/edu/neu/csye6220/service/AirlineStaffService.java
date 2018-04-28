package edu.neu.csye6220.service;

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
public class AirlineStaffService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String username, String password, String email, String airlineCode, String staffCode) {
		AirlineDAO airlineDAO = daoFactory.createAirlineDAO();
		Airline airline = airlineDAO.get(airlineCode);
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(0);
		AirlineStaff airlineStaff = new AirlineStaff(username, password, email, role, airline, staffCode);
		AirlineStaffDAO airlineStaffDAO = daoFactory.createAirlineStaffDAO();
		airlineStaffDAO.add(airlineStaff);
	}
	
	public String getCode(String username) {
		AirlineStaffDAO airlineStaffDAO = daoFactory.createAirlineStaffDAO();
		AirlineStaff airlineStaff = airlineStaffDAO.get(username);
		return airlineStaff.getAirline().getAirlineCode();
	}
}
