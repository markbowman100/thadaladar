package com.markweb.logic;

import java.util.List;
import java.util.Map;

public interface LoginLogic {
	
	public boolean attemptLoginCredentials(String username, String password);
	
	public List<Map<String, Object>> checkIfUserExists(String username);
	
	public boolean insertNewUser(String username, String password, String email);
}
