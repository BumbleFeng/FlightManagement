package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.Flight;

public class FlightDAO extends DAO{
	
	public void add(Flight flight) {
		try {
			begin();
			getSession().save(flight);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Flight> list(Airline airline) {
		List<Flight> flights = null;
		try {
			begin();
			Query q = getSession().createQuery("from Flight where airline= :airline");
			q.setParameter("airline", airline);
			flights = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flights;
	}
	
	public Flight get(String callSign) {
		Flight flight = null;
		try {
			begin();
			Query q = getSession().createQuery("from Flight where callSign= :callSign");
			q.setParameter("callSign", callSign);
			flight = (Flight) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flight;
	}
	
	public Airport departure(String callSign) {
		Airport airport = null;
		try {
			begin();
			Query q = getSession().createQuery("from Flight where callSign= :callSign");
			q.setParameter("callSign", callSign);
			Flight flight = (Flight) q.uniqueResult();
			airport = flight.getDepartureAirport();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airport;
	}
	
	public Airport arrival(String callSign) {
		Airport airport = null;
		try {
			begin();
			Query q = getSession().createQuery("from Flight where callSign= :callSign");
			q.setParameter("callSign", callSign);
			Flight flight = (Flight) q.uniqueResult();
			airport = flight.getArrivalAirport();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airport;
	}
}
