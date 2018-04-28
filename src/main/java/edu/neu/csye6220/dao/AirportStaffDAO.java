package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;

import edu.neu.csye6220.pojo.AirportStaff;

public class AirportStaffDAO extends DAO{
	
	public void add(AirportStaff airportStaff) {
		try {
			begin();
			getSession().save(airportStaff);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
