package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.CheckinCounter;

public class CheckinCounterDAO extends DAO{
	
	public void add(CheckinCounter checkinCounter) {
		try {
			begin();
			getSession().save(checkinCounter);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<CheckinCounter> list() {
		List<CheckinCounter> checkinCounters = null;
		try {
			begin();
			Query q = getSession().createQuery("from CheckinCounter");
			checkinCounters = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return checkinCounters;
	}
}
