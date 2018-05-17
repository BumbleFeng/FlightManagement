package edu.neu.csye6220.service;

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
public class AirportStaffService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(String username, String password, String email, String airportCode, String staffCode) {
		AirportDAO airportDAO = daoFactory.createAirportDAO();
		Airport airport = airportDAO.get(airportCode);
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(0);
		AirportStaff airportStaff = new AirportStaff(username, password, email, role, airport, staffCode);
		AirportStaffDAO airportStaffDAO = daoFactory.createAirportStaffDAO();
		airportStaffDAO.add(airportStaff);
	}
}
