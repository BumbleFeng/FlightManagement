package edu.neu.csye6220.test;


import org.junit.Test;

import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.RoleDAO;
import edu.neu.csye6220.dao.UserDAO;
import edu.neu.csye6220.pojo.Role;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.service.AircraftService;
import edu.neu.csye6220.service.AirlineService;
import edu.neu.csye6220.service.AirportService;
import edu.neu.csye6220.service.CheckinCounterService;
import edu.neu.csye6220.service.FlightService;
import edu.neu.csye6220.service.FlyDutyService;
import edu.neu.csye6220.service.TerminalService;

public class UnitTest {
	private DAOFactory daoFactory;

	public UnitTest() {
		daoFactory = new DAOFactory();
	}

	@Test
	public void roleInit() {
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		roleDAO.add(new Role("ROLE_UNVERIFIED"));
		roleDAO.add(new Role("ROLE_USER"));
		roleDAO.add(new Role("ROLE_ATRPORTSTAFF"));
		roleDAO.add(new Role("ROLE_ATRPORTADMIN"));
		roleDAO.add(new Role("ROLE_ATRLINESTAFF"));
		roleDAO.add(new Role("ROLE_ATRLINEADMIN"));
		Role role = new Role("ROLE_ADMIN");
		roleDAO.add(role);
		User admin = new User("admin", "{noop}admin", "admin@neu.edu", role);
		UserDAO userDAO = daoFactory.createUserDAO();
		userDAO.add(admin);
	}

	@Test
	public void airlineInit() {
		AirlineService airlineService = new AirlineService();
		airlineService.setDaoFactory(daoFactory);
		airlineService.add("DL", "Delta Airlines");
		airlineService.add("UA", "United Airlines");
		airlineService.add("HU", "Hainan Airlines");
	}

	@Test
	public void airportInit() {
		AirportService airportService = new AirportService();
		airportService.setDaoFactory(daoFactory);
		airportService.add("BOS", "General Edward Lawrence Logan International Airport", "Boston", "United States",
				"-05:00");
		airportService.add("DTW", "Detroit Metropolitan Wayne County Airport", "Detroit", "United States", "-05:00");
		airportService.add("EWR", "Newark Liberty International Airport", "Newark", "United States", "-05:00");
		airportService.add("SFO", "San Francisco International Airport", "San Francisco", "United States", "-08:00");
		airportService.add("PEK", "Beijing Capital International Airport", "Beijing", "China", "+08:00");
		airportService.add("PVG", "Shanghai Pudong International Airport", "Shanghai", "China", "+08:00");
	}

	@Test
	public void terminalInit() {
		TerminalService terminalService = new TerminalService();
		terminalService.setDaoFactory(daoFactory);
		terminalService.add("BOS", "A");
		terminalService.add("BOS", "B");
		terminalService.add("BOS", "C");
		terminalService.add("BOS", "D");
		terminalService.add("BOS", "E");
		terminalService.add("DTW", "M");
		terminalService.add("DTW", "N");
		terminalService.add("EWR", "A");
		terminalService.add("EWR", "B");
		terminalService.add("EWR", "C");
		terminalService.add("SFO", "T1");
		terminalService.add("SFO", "T2");
		terminalService.add("SFO", "T3");
		terminalService.add("PEK", "T1");
		terminalService.add("PEK", "T2");
		terminalService.add("PEK", "T3");
		terminalService.add("PVG", "T1");
		terminalService.add("PVG", "T2");
	}

	@Test
	public void checkinCounterInit() {
		CheckinCounterService checkinCounterServiceService = new CheckinCounterService();
		checkinCounterServiceService.setDaoFactory(daoFactory);
		checkinCounterServiceService.add("UA", "SFO", "T3", "1");
		checkinCounterServiceService.add("UA", "PVG", "T2", "2");
		checkinCounterServiceService.add("UA", "EWR", "C", "3");
		checkinCounterServiceService.add("UA", "BOS", "B", "4");
		checkinCounterServiceService.add("DL", "DTW", "M", "5");
		checkinCounterServiceService.add("HU", "BOS", "E", "5");
		checkinCounterServiceService.add("DL", "PVG", "T1", "6");
		checkinCounterServiceService.add("HU", "PVG", "T2", "6");
	}

	@Test
	public void aircraftInit() {
		AircraftService aircraftService = new AircraftService();
		aircraftService.setDaoFactory(daoFactory);
		aircraftService.add("HU", "123HU", "787", 1, 2, 2, 4);
		aircraftService.add("DL", "D333", "A320", 4, 1, 2, 4);
		aircraftService.add("DL", "D777", "744", 6, 2, 3, 4);
		aircraftService.add("DL", "DL7", "A380", 9, 2, 2, 2);
		aircraftService.add("UA", "N178", "747", 20, 1, 2, 6);
		aircraftService.add("UA", "N423", "772", 3, 2, 2, 3);
		aircraftService.add("UA", "N568", "752", 12, 1, 2, 3);
		aircraftService.add("UA", "N684", "73G", 7, 1, 3, 5);
	}

	/*@Test
	public void seatInit() {
		SeatService seatService = new SeatService();
		seatService.setDaoFactory(daoFactory);
		seatService.add("123HU", "F", "1A", 1);
		seatService.add("123HU", "F", "1B", 1);
		seatService.add("123HU", "B", "2A", 2);
		seatService.add("123HU", "B", "2B", 2);
		seatService.add("123HU", "E", "3A", 3);
		seatService.add("123HU", "E", "3B", 3);
		seatService.add("123HU", "E", "4A", 4);
		seatService.add("123HU", "E", "4B", 4);
		seatService.add("D333", "F", "1A", 1);
		seatService.add("D333", "B", "2A", 2);
		seatService.add("D333", "B", "2B", 2);
		seatService.add("D333", "E", "3A", 3);
		seatService.add("D333", "E", "3B", 3);
		seatService.add("D333", "E", "4A", 4);
		seatService.add("D333", "E", "4B", 4);
		seatService.add("D777", "F", "1A", 1);
		seatService.add("D777", "F", "1B", 1);
		seatService.add("D777", "B", "2A", 2);
		seatService.add("D777", "B", "2B", 2);
		seatService.add("D777", "B", "2C", 2);
		seatService.add("D777", "E", "3A", 3);
		seatService.add("D777", "E", "3B", 3);
		seatService.add("D777", "E", "3C", 3);
		seatService.add("D777", "E", "3D", 3);
		seatService.add("DL7", "F", "1A", 1);
		seatService.add("DL7", "F", "1B", 1);
		seatService.add("DL7", "B", "2A", 2);
		seatService.add("DL7", "B", "2B", 2);
		seatService.add("DL7", "E", "3A", 3);
		seatService.add("DL7", "E", "3B", 3);
		seatService.add("N178", "F", "1A", 1);
		seatService.add("N178", "B", "2A", 2);
		seatService.add("N178", "B", "2B", 2);
		seatService.add("N178", "E", "3A", 3);
		seatService.add("N178", "E", "3B", 3);
		seatService.add("N178", "E", "3C", 3);
		seatService.add("N178", "E", "4A", 4);
		seatService.add("N178", "E", "4B", 4);
		seatService.add("N178", "E", "4C", 4);
		seatService.add("N423", "F", "1A", 1);
		seatService.add("N423", "F", "1B", 1);
		seatService.add("N423", "B", "2A", 2);
		seatService.add("N423", "B", "2B", 2);
		seatService.add("N423", "E", "3A", 3);
		seatService.add("N423", "E", "3B", 3);
		seatService.add("N423", "E", "3C", 3);
		seatService.add("N568", "F", "1A", 1);
		seatService.add("N568", "B", "2A", 2);
		seatService.add("N568", "B", "2B", 2);
		seatService.add("N568", "E", "3A", 3);
		seatService.add("N568", "E", "3B", 3);
		seatService.add("N568", "E", "3C", 3);
		seatService.add("N684", "F", "1A", 1);
		seatService.add("N684", "B", "2A", 2);
		seatService.add("N684", "B", "2B", 2);
		seatService.add("N684", "B", "2C", 2);
		seatService.add("N684", "E", "3A", 3);
		seatService.add("N684", "E", "3B", 3);
		seatService.add("N684", "E", "3C", 3);
		seatService.add("N684", "E", "4A", 4);
		seatService.add("N684", "E", "4B", 4);
	}*/
	
	@Test
	public void flightInit() {
		FlightService flightService = new FlightService();
		flightService.setDaoFactory(daoFactory);
		flightService.add("HU7961", "PVG", "BOS", "12:20", "14:15", 11749);
		flightService.add("HU7962", "BOS", "PVG", "15:35", "14:40", 11749);
	}

	@Test
	public void flydutyInit() {
		FlyDutyService flyDutyService = new FlyDutyService();
		flyDutyService.setDaoFactory(daoFactory);
		flyDutyService.add("HU7961", "2018-05-01", "T2", "E", "123HU");
		flyDutyService.add("HU7962", "2018-05-02", "E", "T2", "123HU");
	}
	
}
