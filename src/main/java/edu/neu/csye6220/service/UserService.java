package edu.neu.csye6220.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.neu.csye6220.dao.DAOFactory;
import edu.neu.csye6220.dao.RoleDAO;
import edu.neu.csye6220.dao.UserDAO;
import edu.neu.csye6220.pojo.Role;
import edu.neu.csye6220.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private DAOFactory daoFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public User get(String username) {
		UserDAO userDAO = daoFactory.createUserDAO();
		return userDAO.get(username);
	}
	
	public User getByEmail(String email) {
		UserDAO userDAO = daoFactory.createUserDAO();
		return userDAO.getByEmail(email);
	}
	
	public void add(String username, String password, String email) {
		if(duplicate(username)||duplicateEmail(email)) return;
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(1);
		String pass = passwordEncoder.encode(password);
		User user = new User(username, pass, email, role);
		UserDAO userDAO = daoFactory.createUserDAO();
		userDAO.add(user);	
	} 
	
	public boolean duplicate(String username) {
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.get(username);
		if (user == null)
			return false;
		else
			return true;
	}
	
	public boolean duplicateEmail(String email) {
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.getByEmail(email);
		if (user == null)
			return false;
		else
			return true;
	}
	
	public Boolean updateRole(String email) {
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.getByEmail(email);
		if(user==null) return false;
		RoleDAO roleDAO = daoFactory.createRoleDAO();
		Role role = roleDAO.get(2);
		user.setRole(role);
		return userDAO.update(user);
	}
	
	public Boolean resetPassword(String email,String password) {
		UserDAO userDAO = daoFactory.createUserDAO();
		User user = userDAO.getByEmail(email);
		if(user==null) return false;
		user.setPassword(passwordEncoder.encode(password));
		return userDAO.update(user);
	}
}
