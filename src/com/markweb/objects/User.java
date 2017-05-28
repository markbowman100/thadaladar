package com.markweb.objects;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	@NotBlank
	String username;
	@NotBlank
	String password;
	List<String> random;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRandom() {
		return random;
	}

	public void setRandom(List<String> random) {
		this.random = random;
	}
	
}
