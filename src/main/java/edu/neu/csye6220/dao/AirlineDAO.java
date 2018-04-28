package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Airline;

public class AirlineDAO extends DAO {

	public void add(Airline airline) {
		try {
			begin();
			getSession().save(airline);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Airline> list() {
		List<Airline> airlines = null;
		try {
			begin();
			Query q = getSession().createQuery("from Airline");
			airlines = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airlines;
	}
	
	public Airline get(String airlineCode) {
		Airline airline = null;
		try {
			begin();
			Query q = getSession().createQuery("from Airline where airlineCode= :airlineCode");
			q.setParameter("airlineCode", airlineCode);
			airline = (Airline) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airline;
	}
	
}
