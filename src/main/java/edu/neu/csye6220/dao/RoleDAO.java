package edu.neu.csye6220.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import edu.neu.csye6220.pojo.Role;

public class RoleDAO extends DAO{
	
	public void add(Role role) {
		try {
			begin();
			getSession().save(role);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Role get(int id) {
		Role role = null;
		try {
			begin();
			Query q = getSession().createQuery("from Role where Role_ID= :Role_ID");
			q.setParameter("Role_ID", id);
			role = (Role) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return role;
	}
}
