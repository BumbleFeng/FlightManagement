package edu.neu.csye6220.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.ItineraryDAO;
import edu.neu.csye6220.dao.PassengerDAO;
import edu.neu.csye6220.dao.TicketDAO;
import edu.neu.csye6220.dao.UserOrderDAO;
import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.pojo.Itinerary;
import edu.neu.csye6220.pojo.Passenger;
import edu.neu.csye6220.pojo.Ticket;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.pojo.UserOrder;

@Service
public class UserOrderService {

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void add(User user, String[] realIds, FlyDuty flyDuty, String seatClass) {
		char c = seatClass.charAt(0);
		int s = (int) c;
		PassengerDAO passengerDAO = daoFactory.createPassengerDAO();
		UserOrder userOrder = new UserOrder(LocalDateTime.now(), user);
		UserOrderDAO userOrderDAO = daoFactory.createUserOrderDAO();
		userOrderDAO.add(userOrder);
		ItineraryDAO itineraryDAO = daoFactory.createItineraryDAO();
		TicketDAO ticketDAO = daoFactory.createTicketDAO();
		for (String realId : realIds) {
			Passenger passenger = passengerDAO.get(realId);
			Itinerary itinerary = new Itinerary(passenger, userOrder);
			itineraryDAO.add(itinerary);
			double price = 0;
			switch (s) {
			case 70:
				price = flyDuty.getFirstclassPrice();
				break;
			case 66:
				price = flyDuty.getBusinessPrice();
				break;
			case 69:
				price = flyDuty.getEconomyPrice();
				break;
			default:
				break;
			}
			userOrder.setPrice(userOrder.getPrice() + price);
			Ticket ticket = new Ticket(flyDuty, itinerary, seatClass, price);
			ticketDAO.add(ticket);
			itinerary.setPrice(price);
			itineraryDAO.update(itinerary);
		}
		userOrderDAO.update(userOrder);
	}

	public UserOrder[] list(User user) {
		UserOrderDAO userOrderDAO = daoFactory.createUserOrderDAO();
		List<UserOrder> userOrders = userOrderDAO.list(user);
		int s = userOrders.size();
		UserOrder[] ordered = new UserOrder[s];
		for (int i = 0; i < s; i++) {
			ordered[i] = userOrders.get(s - i - 1);
		}
		return ordered;
	}
}
