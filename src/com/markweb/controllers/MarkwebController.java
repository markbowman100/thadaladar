package com.markweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.markweb.objects.Register;
import com.markweb.objects.User;

@EnableWebMvc
@Controller
@RequestMapping("/")
public class MarkwebController {
	
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
		
		try {
			if (user.getUsername().equals("Jamzo") && user.getPassword().equals("aaaaaaa1")) {
				session.setAttribute("username", user.getUsername());
				pageName = "redirect:/welcome";
			}
		} catch(Exception e) {
			
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Register user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "register";
		
		try {
			if (user.getUsername().equals("Jamzo") && user.getPassword().equals("aaaaaaa1")) {
				session.setAttribute("username", user.getUsername());
				pageName = "redirect:/welcome";
			}
		} catch(Exception e) {
			
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid Register user, BindingResult bindingresult, HttpSession session) {
		
		String pageName = "register";
		
		if (!bindingresult.hasErrors()) {
			pageName = "redirect:/";
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/welcome")
    public String welcome(HttpSession session) {
		
		String pageName = "redirect:/";
		
		System.out.println(session.getAttribute("username"));
		
		if (session.getAttribute("username") != null) {
			pageName = "welcome";
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
