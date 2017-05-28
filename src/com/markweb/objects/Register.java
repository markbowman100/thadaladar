package com.markweb.objects;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Register {
	
	@NotBlank(message = "Must Not Be Blank")
	@Size(min=2, max=30, message = "Must be between 2 and 30 characters")
	String username;
	@NotBlank(message = "Must Not Be Blank")
	@Pattern(regexp = "^(?=.*\\d).{8,30}$", message = "Password must be between 8 and 30 characters and contain 1 number")
	String password;
	String confirmPassword;
	@Email
	@NotBlank(message = "Must Not Be Blank")
	String email;
	@Email
	@NotBlank(message = "Must Not Be Blank")
	String confirmEmail;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	
	@AssertTrue(message="password should match retyped password")
    public boolean isValid(){
        if (password == null) {
            return confirmPassword == null;
        } else {
            return password.equals(confirmPassword);
        }
    }
	
	
	
}
