package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Aircraft;
import edu.neu.csye6220.pojo.Airline;

public class AircraftDAO extends DAO{
	
	public void add(Aircraft aircraft) {
		try {
			begin();
			getSession().save(aircraft);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Aircraft> list(Airline airline) {
		List<Aircraft> aircrafts = null;
		try {
			begin();
			Query q = getSession().createQuery("from Aircraft where airline= :airline");
			q.setParameter("airline", airline);
			aircrafts = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return aircrafts;
	}
	
	public Aircraft get(String aircraftCode) {
		Aircraft aircraft = null;
		try {
			begin();
			Query q = getSession().createQuery("from Aircraft where aircraftCode= :aircraftCode");
			q.setParameter("aircraftCode", aircraftCode);
			aircraft = (Aircraft) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return aircraft;
	}
	
}
