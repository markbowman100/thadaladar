package com.markweb.logic;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.markweb.config.LoginConfig;
import com.markweb.dao.LoginDao;
import com.markweb.dao.LoginDaoImpl;

public class LoginLogicImpl implements LoginLogic {
	
	private LoginConfig config = new LoginConfig();
	private BCryptPasswordEncoder passwordEncoder = config.getBCryptPasswordEncoder();
	
	private LoginDao dao = new LoginDaoImpl();
	private static Logger log = Logger.getLogger("com.markweb.controller");

	@Override
	public boolean attemptLoginCredentials(String username, String password) {
		boolean success = false;
		
		List<Map<String, Object>> resultSet = dao.attemptLoginCredentials(username, password);
		
		if (resultSet != null) {
			if (!resultSet.isEmpty()) {
				if (passwordEncoder.matches(password, (String) resultSet.get(0).get("Password"))) {
					success = true;
				}
			}
		}
		
		return success;
	}

	@Override
	public List<Map<String, Object>> checkIfUserExists(String username) {
		return dao.checkIfUserExists(username);
	}

	@Override
	public boolean insertNewUser(String username, String password, String email) {
		System.out.println(passwordEncoder.encode(password));
		return dao.insertNewUser(username, passwordEncoder.encode(password), email);
	}

}
