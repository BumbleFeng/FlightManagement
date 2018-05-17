package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Passenger;
import edu.neu.csye6220.pojo.User;

public class PassengerDAO extends DAO{
	
	public void add(Passenger passenger) {
		try {
			begin();
			getSession().save(passenger);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Passenger get(String realId) {
		Passenger passenger = null;
		try {
			begin();
			Query q = getSession().createQuery("from Passenger where realId= :realId");
			q.setParameter("realId", realId);
			passenger = (Passenger) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return passenger;
	}
	
	public List<Passenger> list(User user) {
		List<Passenger> passengers = null;
		try {
			begin();
			Query q = getSession().createQuery("from Passenger where user= :user");
			q.setParameter("user", user);
			passengers = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return passengers;
	}
}
