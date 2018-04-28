package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.AirlineStaff;

public class AirlineStaffDAO extends DAO{
	
	public void add(AirlineStaff airlineStaff) {
		try {
			begin();
			getSession().save(airlineStaff);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public AirlineStaff get(String username) {
		AirlineStaff airlineStaff = null;
		try {
			begin();
			Query q = getSession().createQuery("from AirlineStaff where username= :username");
			q.setParameter("username", username);
			airlineStaff = (AirlineStaff) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return airlineStaff;
	}

}
