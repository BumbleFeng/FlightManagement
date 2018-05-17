package edu.neu.csye6220.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Passenger;
import edu.neu.csye6220.pojo.User;
import edu.neu.csye6220.pojo.UserOrder;

public class UserOrderDAO extends DAO{
	
	public void add(UserOrder userOrder) {
		try {
			begin();
			getSession().save(userOrder);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void update(UserOrder userOrder) {
		try {
			begin();
			getSession().update(userOrder);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<UserOrder> list(User user) {
		List<UserOrder> userOrders = null;
		try {
			begin();
			Query q = getSession().createQuery("from UserOrder where user= :user");
			q.setParameter("user", user);
			userOrders = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return userOrders;
	}
}
