package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;

import edu.neu.csye6220.pojo.Itinerary;

public class ItineraryDAO extends DAO{
	
	public void add(Itinerary itinerary) {
		try {
			begin();
			getSession().save(itinerary);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void update(Itinerary itinerary) {
		try {
			begin();
			getSession().update(itinerary);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
}
