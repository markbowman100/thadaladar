package com.markweb.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.markweb.logic.LoginLogic;
import com.markweb.logic.LoginLogicImpl;
import com.markweb.objects.Register;
import com.markweb.objects.User;

@EnableWebMvc
@Controller
public class LoginController {
	
	private LoginLogic logic = new LoginLogicImpl();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloGet(User user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "login";
		
		if (session.getAttribute("username") != null) {
			pageName = "redirect:/welcome";
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public String helloPost(@Valid User user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "login";
		
		boolean success = logic.attemptLoginCredentials(user.getUsername(), user.getPassword());
		
		if (success) {
			session.setAttribute("username", user.getUsername());
			pageName = "redirect:/welcome";
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Register user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "register";
		
		if (session.getAttribute("username") != null) {
			pageName = "redirect:/welcome";
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid Register user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "register";
		
		if (!bindingresult.hasErrors()) {
			List<Map<String, Object>> resultSet = logic.checkIfUserExists(user.getUsername());
			
			if (!resultSet.isEmpty()) {
				bindingresult.addError(new ObjectError("username", "Username already exists, try a different one"));
			}
			else {
				boolean success = logic.insertNewUser(user.getUsername(), user.getPassword(), user.getEmail());
				if (success) {
					pageName = "redirect:/";
				}
			}
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/welcome")
    public String welcome(HttpSession session) {
		
		String pageName = "welcome";
		
		System.out.println(session.getAttribute("username"));
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
        return pageName;
    }
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		String pageName = "redirect:/";
		
		session.invalidate();
		
		return pageName;
	}
	
}
