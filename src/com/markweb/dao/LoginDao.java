package com.markweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

public interface LoginDao {
	
	public List<Map<String, Object>> attemptLoginCredentials(String username, String password);
	
	public List<Map<String, Object>> checkIfUserExists(String username);
	
	public boolean insertNewUser(String username, String password, String email);
}
