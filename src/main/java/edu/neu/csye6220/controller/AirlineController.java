package edu.neu.csye6220.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.AirlineStaff;
import edu.neu.csye6220.pojo.Flight;
import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.service.AircraftService;
import edu.neu.csye6220.service.AirlineStaffService;
import edu.neu.csye6220.service.AirportService;
import edu.neu.csye6220.service.FlightService;
import edu.neu.csye6220.service.FlyDutyService;

@Controller
public class AirlineController {

	@Autowired
	private AircraftService aircraftService;

	@Autowired
	private AirlineStaffService airlineStaffService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportService airportService;

	@Autowired
	private FlyDutyService flyDutyService;

	@RequestMapping(value = "/airline/airline.htm", method = RequestMethod.GET)
	public String airline(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		AirlineStaff airlineStaff = (AirlineStaff) session.getAttribute("user");
		session.setAttribute("airlineCode", airlineStaff.getAirline().getAirlineCode());
		if(airlineStaff.getPassword().startsWith("{noop}")) {
			model.addAttribute("unsafe", true);
		}
		return "airline";
	}

	@RequestMapping(value = "/airline/addAircraft.htm", method = RequestMethod.GET)
	public String aircraft(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String airlineCode = (String) session.getAttribute("airlineCode");
		List<Aircraft> aircrafts = aircraftService.list(airlineCode);
		model.addAttribute("aircrafts", aircrafts);
		return "add-aircraft";
	}

	@RequestMapping(value = "/airline/addAircraft.htm", method = RequestMethod.POST)
	public String addAircraft(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String airlineCode = (String) session.getAttribute("airlineCode");
		String aircraftCode = request.getParameter("aircraftCode");
		String aircraftmodel = request.getParameter("model");
		String age = request.getParameter("age");
		String firstclassSeats = request.getParameter("firstclassSeats");
		String businessSeats = request.getParameter("businessSeats");
		String economicSeats = request.getParameter("economicSeats");
		aircraftService.add(airlineCode, aircraftCode, aircraftmodel, Integer.parseInt(age),
				Integer.parseInt(firstclassSeats), Integer.parseInt(businessSeats), Integer.parseInt(economicSeats));
		return "redirect:/airline/addAircraft.htm";
	}

	@RequestMapping(value = "/airline/addFlight.htm", method = RequestMethod.GET)
	public String flight(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String airlineCode = (String) session.getAttribute("airlineCode");
		List<Flight> flights = flightService.list(airlineCode);
		model.addAttribute("flights", flights);
		return "add-flight";
	}

	@RequestMapping(value = "/airline/addFlight.htm", method = RequestMethod.POST)
	public String addFlight(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String airlineCode = (String) session.getAttribute("airlineCode");
		String callSign = request.getParameter("callSign");
		String departureAirport = request.getParameter("departureAirport");
		String arrivalAirport = request.getParameter("arrivalAirport");
		String scheduledDeparture = request.getParameter("scheduledDeparture");
		String flightHour = request.getParameter("flightHour");
		String mileage = request.getParameter("mileage");
		if (!departureAirport.equals("?") && !arrivalAirport.equals("?") && !departureAirport.equals(arrivalAirport)
				&& callSign.substring(0, 2).toUpperCase().equals(airlineCode)) {
			String departureAirportCode = airportService.list().get(Integer.parseInt(departureAirport))
					.getAirportCode();
			String arrivalAirportCode = airportService.list().get(Integer.parseInt(arrivalAirport)).getAirportCode();
			flightService.add(callSign, departureAirportCode, arrivalAirportCode, scheduledDeparture, flightHour,
					Integer.parseInt(mileage));
		}
		return "redirect:/airline/addFlight.htm";
	}

	@RequestMapping(value = "/airline/addFlyDuty.htm", method = RequestMethod.GET)
	public String flyDuty(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String airlineCode = (String) session.getAttribute("airlineCode");
		model.addAttribute("airlineCode", airlineCode);
		List<FlyDuty> flyDuties = flyDutyService.list(airlineCode);
		model.addAttribute("flyDuties", flyDuties);
		return "add-flyduty";
	}

	@RequestMapping(value = "/airline/addFlyDuty.htm", method = RequestMethod.POST)
	public String addFlyDuty(HttpServletRequest request, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		List<Flight> flights = flightService.list(airlineCode);
		List<Aircraft> aircrafts = aircraftService.list(airlineCode);
		String callSign = request.getParameter("callSign");
		String departureTerminal = request.getParameter("departureTerminal");
		String arrivalTerminal = request.getParameter("arrivalTerminal");
		String aircraft = request.getParameter("aircraft");
		String date = request.getParameter("date");
		if (!callSign.equals("?") && !departureTerminal.equals("?") && !arrivalTerminal.equals("?")
				&& !aircraft.equals("?")) {
			Flight flight = flights.get(Integer.parseInt(callSign));
			String c = flight.getCallSign();
			String departureTerminalCode = flight.getDepartureAirport().getTerminals()
					.get(Integer.parseInt(departureTerminal)).getTerminalCode();
			String arrivalTerminalCode = flight.getArrivalAirport().getTerminals()
					.get(Integer.parseInt(arrivalTerminal)).getTerminalCode();
			String aircraftCode = aircrafts.get(Integer.parseInt(aircraft)).getAircraftCode();
			flyDutyService.add(c, date, departureTerminalCode, arrivalTerminalCode, aircraftCode);
		}
		return "redirect:/airline/addFlyDuty.htm";
	}

	@RequestMapping(value = "/airline/sales.htm", method = RequestMethod.GET)
	public String sales(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		model.addAttribute("airlineCode", airlineCode);
		List<FlyDuty> flyDuties = flyDutyService.list(airlineCode);
		for(FlyDuty flyDuty:flyDuties) {
			flyDutyService.updateOrder(flyDuty);
		}
		model.addAttribute("flyDuties", flyDuties);
		return "sales";
	}

}
