package edu.neu.csye6220.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.PassengerDAO;
import edu.neu.csye6220.pojo.Passenger;
import edu.neu.csye6220.pojo.User;

@Service
public class PassengerService {
	
	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public void add(String realId, String firstName, String lastName, String birthday, User user) {
		if (duplicate(realId))
			return;
		PassengerDAO passengerDAO = daoFactory.createPassengerDAO();
		LocalDate birthdate = LocalDate.parse(birthday);
		Passenger passenger = new Passenger(realId, firstName, lastName, birthdate, user);
		passengerDAO.add(passenger);
	}
	
	public List<Passenger> list(User user){
		PassengerDAO passengerDAO = daoFactory.createPassengerDAO();
		return passengerDAO.list(user);
	}
	
	public boolean duplicate(String realId) {
		PassengerDAO passengerDAO = daoFactory.createPassengerDAO();
		Passenger passenger = passengerDAO.get(realId);
		if (passenger == null)
			return false;
		else
			return true;
	}
}
