package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Gate;
import edu.neu.csye6220.pojo.Terminal;

public class GateDAO extends DAO{
	
	public void add(Gate gate) {
		try {
			begin();
			getSession().save(gate);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Gate undefined(Terminal terminal) {
		Gate gate = null;
		try {
			begin();
			Query q = getSession().createQuery("from Gate where terminal= :terminal and gateCode= :gateCode");
			q.setParameter("terminal", terminal);
			q.setParameter("gateCode", "undefined");
			gate = (Gate) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return gate;
	}
}
