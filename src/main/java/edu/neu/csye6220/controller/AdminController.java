package edu.neu.csye6220.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.CheckinCounter;
import edu.neu.csye6220.pojo.Terminal;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.service.AirlineService;
import edu.neu.csye6220.service.AirportService;
import edu.neu.csye6220.service.CheckinCounterService;
import edu.neu.csye6220.service.TerminalService;

@Controller
public class AdminController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private AirportService airportService;
	
	@Autowired
	private TerminalService terminalService;
	
	@Autowired
	private CheckinCounterService checkinCounterService;

	@RequestMapping(value = "/admin/admin.htm", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user.getPassword().startsWith("{noop}")) {
			model.addAttribute("unsafe", true);
		}
		return "admin";
	}

	@RequestMapping(value = "/admin/addAirline.htm", method = RequestMethod.GET)
	public String airline(Model model) {
		List<Airline> airlines = airlineService.list();
		model.addAttribute("airlines", airlines);
		return "add-airline";
	}

	@RequestMapping(value = "/admin/addAirline.htm", method = RequestMethod.POST)
	public String addAirline(HttpServletRequest request, Model model) {
		String airlineCode = request.getParameter("airlineCode");
		String airlineName = request.getParameter("airlineName");
		airlineService.add(airlineCode, airlineName);
		return "redirect:/admin/addAirline.htm";
	}

	@RequestMapping(value = "/admin/addAirport.htm", method = RequestMethod.GET)
	public String airport(Model model) {
		List<Airport> airports = airportService.list();
		model.addAttribute("airports", airports);
		return "add-airport";
	}

	@RequestMapping(value = "/admin/addAirport.htm", method = RequestMethod.POST)
	public String addAirport(HttpServletRequest request, Model model) {
		String airportCode = request.getParameter("airportCode");
		String airportName = request.getParameter("airportName");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String timeZone = request.getParameter("timeZone");
		String terminal[] = request.getParameter("terminals").split(",");
		airportService.add(airportCode, airportName, city, country, timeZone);
		for(String t:terminal) {
			terminalService.add(airportCode, t);
		}
		return "redirect:/admin/addAirport.htm";
	}
	
	@RequestMapping(value = "/admin/addCheckinCounter.htm", method = RequestMethod.GET)
	public String checkinCounter(Model model) {
		List<CheckinCounter> checkinCounters = checkinCounterService.list();
		model.addAttribute("checkinCounters", checkinCounters);
		return "add-checkincounter";
	}
	
	@RequestMapping(value = "/admin/addCheckinCounter.htm", method = RequestMethod.POST)
	public String addCheckinCounter(HttpServletRequest request, Model model) {
		String airlineCode = request.getParameter("airlineCode");
		String airportCode = request.getParameter("airportCode");
		String terminalCode = request.getParameter("terminalCode");
		String checkinCounterCode = request.getParameter("checkinCounterCode");
		if(!airlineCode.equals("?")&&!airportCode.equals("?")&&!terminalCode.equals("?")) {
			Airline airline = airlineService.list().get(Integer.parseInt(airlineCode));
			Airport airport = airportService.list().get(Integer.parseInt(airportCode));
			Terminal terminal = airport.getTerminals().get(Integer.parseInt(terminalCode));
			checkinCounterService.add(airline, terminal, checkinCounterCode);
		}
		return "redirect:/admin/addCheckinCounter.htm";
	}
}
