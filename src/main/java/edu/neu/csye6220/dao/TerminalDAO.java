package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Airport;
import edu.neu.csye6220.pojo.Terminal;

public class TerminalDAO extends DAO{
	
	public void add(Terminal terminal) {
		try {
			begin();
			getSession().save(terminal);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Terminal get(Airport airport, String terminalCode) {
		Terminal terminal = null;
		try {
			begin();
			Query q = getSession().createQuery("from Terminal where airport = :airport and terminalCode= :terminalCode");
			q.setParameter("airport", airport);
			q.setParameter("terminalCode", terminalCode);
			terminal = (Terminal) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return terminal;
	}
}
