package com.markweb.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.markweb.config.LoginConfig;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	private LoginConfig config = new LoginConfig();
	private DataSource source = config.getDataSource();
	private JdbcTemplate template = config.getJdbcTemplate(source);
	private static Logger log = Logger.getLogger("com.markweb.controller");
	
	@Override
	public List<Map<String, Object>> attemptLoginCredentials(String username, String password) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		
		try {
			String sql = "SELECT Username, Password FROM User WHERE Username = ?;";
			
			resultSet = template.queryForList(sql, new Object[] {username}, new int[] {Types.VARCHAR});
			
		} catch(Exception e) {
			log.info("JourneyDaoImpl attemptLoginCredentials " + e);
		}
		
		
			
		return resultSet;
	}

	@Override
	public List<Map<String, Object>> checkIfUserExists(String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		
		try {
			String sql = "SELECT * FROM User WHERE Username = ?;";
			
			resultSet = template.queryForList(sql,  new Object[] {username}, new int[] {Types.VARCHAR});
			
		} catch(Exception e) {
			log.info("JourneyDaoImpl checkIfUserExists " + e);
		}
	
		return resultSet;
	}

	@Override
	public boolean insertNewUser(String username, String password, String email) {
		boolean success = false;
		
		try {
			String sql = "INSERT INTO User(Username, Password, Email) VALUES( ?, ?, ?);";
			
			template.update(sql, new Object[] {username, password, email}, new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
			
			success = true;
			
		} catch(Exception e) {
			log.info("JourneyDaoImpl insertNewUser " + e);
		}
		
		return success;
	}

}
