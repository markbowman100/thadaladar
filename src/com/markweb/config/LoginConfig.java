package com.markweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public DataSource getDataSource() {
		
	    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	    return lookup.getDataSource("jdbc/thadalod_primary");
	    
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) { 
	    return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
