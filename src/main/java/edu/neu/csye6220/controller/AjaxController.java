package edu.neu.csye6220.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.Flight;
import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.service.AircraftService;
import edu.neu.csye6220.service.AirlineService;
import edu.neu.csye6220.service.AirlineStaffService;
import edu.neu.csye6220.service.AirportService;
import edu.neu.csye6220.service.FlightService;
import edu.neu.csye6220.service.FlyDutyService;
import edu.neu.csye6220.service.PassengerService;
import edu.neu.csye6220.service.UserService;

@Controller
public class AjaxController {

	@Autowired
	private AirlineService airlineService;

	@Autowired
	private AirportService airportService;
	
	@Autowired
	private AircraftService aircraftService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private AirlineStaffService airlineStaffService;
	
	@Autowired
	private FlyDutyService flyDutyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PassengerService passengerService;

	public AjaxController() {
	}

	@RequestMapping(value = "/**/checkAirline.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkAirline(HttpServletRequest request) {
		String code = request.getParameter("code");
		if (airlineService.duplicate(code))
			return "duplicate";
		else
			return "available";
	}

	@RequestMapping(value = "/**/checkAirport.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkAirport(HttpServletRequest request) {
		String code = request.getParameter("code").substring(0, 3).toUpperCase();
		if (airportService.duplicate(code))
			return "duplicate";
		else
			return "available";
	}
	
	@RequestMapping(value = "/**/checkAircraft.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkAircraft(HttpServletRequest request) {
		String code = request.getParameter("code").toUpperCase();
		if (aircraftService.duplicate(code))
			return "duplicate";
		else
			return "available";
	}
	
	@RequestMapping(value = "/**/checkFlight.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkFlight(HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		String code = request.getParameter("code").toUpperCase();
		if(!code.substring(0, 2).toUpperCase().equals(airlineCode)) {
			return "invalid";
		}
		if (flightService.duplicate(code))
			return "duplicate";
		else
			return "available";
	}
	
	@RequestMapping(value = "/**/checkUsername.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkUsername(HttpServletRequest request) {
		String username = request.getParameter("username");
		if (userService.duplicate(username))
			return "duplicate";
		else
			return "available";
	}
	
	@RequestMapping(value = "/**/checkEmail.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(HttpServletRequest request) {
		String email = request.getParameter("email");
		if (userService.duplicateEmail(email))
			return "registered";
		else
			return "available";
	}
	
	@RequestMapping(value = "/**/checkRealId.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkRealId(HttpServletRequest request) {
		String realId = request.getParameter("realId");
		if (passengerService.duplicate(realId))
			return "duplicate";
		else
			return "available";
	}

	@RequestMapping(value = "/**/airlineList.htm", method = RequestMethod.GET)
	@ResponseBody
	public String airlineList(HttpServletRequest request){
		List<Airline> airlines = airlineService.list();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(airlines);
	}
	
	@RequestMapping(value = "/**/airportList.htm", method = RequestMethod.GET)
	@ResponseBody
	public String airportList(HttpServletRequest request){
		List<Airport> airports = airportService.list();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(airports);
	}
	
	@RequestMapping(value = "/**/aircraftList.htm", method = RequestMethod.GET)
	@ResponseBody
	public String aircraftList(HttpServletRequest request){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		List<Aircraft> aircrafts = aircraftService.list(airlineCode);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(aircrafts);
	}
	
	@RequestMapping(value = "/**/flightList.htm", method = RequestMethod.GET)
	@ResponseBody
	public String flightList(HttpServletRequest request){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		List<Flight> flights = flightService.list(airlineCode);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(flights);
	}
	
	@RequestMapping(value = "/**/cityList.htm", method = RequestMethod.POST)
	@ResponseBody
	public String city(HttpServletRequest request) {
		List<Airport> airports = airportService.list();
		String queryString = request.getParameter("city");
		String result = "";
		for(int i = 0; i<airports.size();i++) {
			if((airports.get(i).getCity()).toLowerCase().contains(queryString.toLowerCase())){
				result+=airports.get(i).getCity()+",";
			}
		}
		return result;
		
	}
	
	@RequestMapping(value = "/**/updatePrices.htm", method = RequestMethod.POST)
	@ResponseBody
	public String updatePrices(HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String airlineCode = airlineStaffService.getCode(userDetails.getUsername());
		List<FlyDuty> flyDuties = flyDutyService.list(airlineCode);
		Gson gson = new Gson();
		String[] price = gson.fromJson(request.getParameter("prices"), String[].class);
		for (int i = 0; i < price.length; i += 3) {
			flyDuties.get(i/3).setFirstclassPrice(Double.parseDouble(price[i]));
			flyDuties.get(i/3).setBusinessPrice(Double.parseDouble(price[i+1]));
			flyDuties.get(i/3).setEconomyPrice(Double.parseDouble(price[i+2]));
		}
		for(FlyDuty flyDuty:flyDuties) {
			flyDutyService.update(flyDuty);
		}
		return null;
	}
	
}
