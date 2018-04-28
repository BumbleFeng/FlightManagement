package edu.neu.csye6220.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.pojo.Passenger;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.pojo.UserOrder;
import edu.neu.csye6220.service.FlyDutyService;
import edu.neu.csye6220.service.UserOrderService;
import edu.neu.csye6220.service.PassengerService;

@Controller
public class UserController {

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private FlyDutyService flyDutyService;

	@Autowired
	private UserOrderService userOrderService;

	@RequestMapping(value = "/user/addPassenger.htm", method = RequestMethod.GET)
	public String passenger(HttpServletRequest request, Model Model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Passenger> passengers = passengerService.list(user);
		Model.addAttribute("passengers", passengers);
		return "add-passenger";
	}

	@RequestMapping(value = "/user/addPassenger.htm", method = RequestMethod.POST)
	public String addPassenger(HttpServletRequest request, Model Model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String realId = request.getParameter("realId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		passengerService.add(realId, firstName, lastName, birthday, user);
		return "redirect:/user/addPassenger.htm";
	}

	@RequestMapping(value = "/user/bookTickets.htm", method = RequestMethod.GET)
	public String bookTickets(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id = (String) session.getAttribute("id");
		FlyDuty flyDuty = flyDutyService.getById(id);
		model.addAttribute("flyDuty", flyDuty);
		List<Passenger> passengers = passengerService.list(user);
		model.addAttribute("passengers", passengers);
		return "book-tickets";
	}

	@RequestMapping(value = "/user/bookTickets.htm", method = RequestMethod.POST)
	public String bookTicketsForm(HttpServletRequest request, Model Model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String realId = request.getParameter("realId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		passengerService.add(realId, firstName, lastName, birthday, user);
		return "redirect:/user/bookTickets.htm";
	}

	@RequestMapping(value = "/user/orderTickets.htm", method = RequestMethod.POST)
	public String orderTicketsForm(HttpServletRequest request, Model Model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id = (String) session.getAttribute("id");
		FlyDuty flyDuty = flyDutyService.getById(id);
		String seatClass = request.getParameter("class");
		String[] realIds = request.getParameterValues("realIds");
		int p = realIds.length;
		if (soldOut(flyDuty, seatClass, p)) {
			return "redirect:/searchFlights.htm";
		}
		userOrderService.add(user, realIds, flyDuty, seatClass);
		return "redirect:/user/orders.htm";
	}

	public boolean soldOut(FlyDuty flyDuty, String seatClass, int p) {
		char c = seatClass.charAt(0);
		int s = (int) c;
		switch (s) {
		case 70:
			return flyDuty.getFirstclassRemain() < p;
		case 66:
			return flyDuty.getBusinessPrice() < p;
		case 69:
			return flyDuty.getEconomyRemain() < p;
		}
		return false;
	}

	@RequestMapping(value = "/user/orders.htm", method = RequestMethod.GET)
	public String orders(HttpServletRequest request, Model Model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String id = (String) session.getAttribute("id");
		if (id != null) {
			FlyDuty flyDuty = flyDutyService.getById(id);
			flyDutyService.updateOrder(flyDuty);
		}
		UserOrder[] userOders = userOrderService.list(user);
		Model.addAttribute("userOders", userOders);
		return "userorders";
	}
}
