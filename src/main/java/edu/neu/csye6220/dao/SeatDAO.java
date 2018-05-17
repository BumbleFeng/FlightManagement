package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;

import edu.neu.csye6220.pojo.Seat;

public class SeatDAO extends DAO{
	
	public void add(Seat seat) {
		try {
			begin();
			getSession().save(seat);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
