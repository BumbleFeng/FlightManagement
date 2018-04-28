package edu.neu.csye6220.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Airline;
import edu.neu.csye6220.pojo.Flight;
import edu.neu.csye6220.pojo.FlyDuty;

public class FlyDutyDAO extends DAO {

	public void add(FlyDuty flyDuty) {
		try {
			begin();
			getSession().save(flyDuty);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public FlyDuty get(Flight flight, LocalDate date) {
		FlyDuty flyDuty = null;
		try {
			begin();
			Query q = getSession().createQuery("from FlyDuty where flight = :flight and date= :date");
			q.setParameter("flight", flight);
			q.setParameter("date", date);
			flyDuty = (FlyDuty) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flyDuty;
	}
	
	public FlyDuty getById(Long id) {
		FlyDuty flyDuty = null;
		try {
			begin();
			Query q = getSession().createQuery("from FlyDuty where id = :id");
			q.setParameter("id", id);
			flyDuty = (FlyDuty) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flyDuty;
	}

	public List<FlyDuty> list(Airline airline) {
		List<FlyDuty> flyDuties = null;
		try {
			begin();
			Query q = getSession().createQuery("from FlyDuty where flight.airline= :airline");
			q.setParameter("airline", airline);
			flyDuties = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flyDuties;
	}

	public void update(FlyDuty flyDuty) {
		try {
			begin();
			getSession().update(flyDuty);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<FlyDuty> search(String departureCity, String arrivalCity, LocalDate date) {
		List<FlyDuty> flyDuties = null;
		try {
			begin();
			Query q = getSession().createQuery(
					"from FlyDuty where flight.departureAirport.city= :departureCity and flight.arrivalAirport.city= :arrivalCity and date= :date");
			q.setParameter("departureCity", departureCity);
			q.setParameter("arrivalCity", arrivalCity);
			q.setParameter("date", date);
			flyDuties = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return flyDuties;
	}
}
