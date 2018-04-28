package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Airport;

public class AirportDAO extends DAO {

	public void add(Airport airport) {
		try {
			begin();
			getSession().save(airport);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Airport> list() {
		List<Airport> airports = null;
		try {
			begin();
			Query q = getSession().createQuery("from Airport");
			airports = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airports;
	}

	public Airport get(String airportCode) {
		Airport airport = null;
		try {
			begin();
			Query q = getSession().createQuery("from Airport where airportCode= :airportCode");
			q.setParameter("airportCode", airportCode);
			airport = (Airport) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airport;
	}
	
	public Airport search(String city) {
		Airport airport = null;
		try {
			begin();
			Query q = getSession().createQuery("from Airport where city= :city");
			q.setParameter("city", city);
			airport = (Airport) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airport;
	}
}
