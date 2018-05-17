package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.FlyDuty;
import edu.neu.csye6220.pojo.Ticket;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.pojo.UserOrder;

public class TicketDAO extends DAO{
	
	public void add(Ticket ticket) {
		try {
			begin();
			getSession().save(ticket);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Ticket> list(FlyDuty flyDuty) {
		List<Ticket> tickets = null;
		try {
			begin();
			Query q = getSession().createQuery("from Ticket where flyDuty= :flyDuty");
			q.setParameter("flyDuty", flyDuty);
			tickets = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return tickets;
	}
}
