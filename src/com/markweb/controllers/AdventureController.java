package com.markweb.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.markweb.logic.AdventureLogic;
import com.markweb.logic.AdventureLogicImpl;
import com.markweb.objects.Campaign;
import com.markweb.objects.User;

@EnableWebMvc
@Controller
public class AdventureController {
	
	private AdventureLogic logic = new AdventureLogicImpl();
	private static Logger log = Logger.getLogger("com.markweb.controller");
	
	//Campaign Wireframe URL: https://wireframe.cc/DcS067
	
	@RequestMapping(value = "/adventure", method = RequestMethod.GET)
    public String adventuresGet(User user, BindingResult bindingresult, Model model, HttpSession session) {
		
		String pageName = "campaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		else {
			
		}
		
		List<Campaign> campaigns = logic.getCampaign((String) session.getAttribute("username")); 
		model.addAttribute("campaigns", campaigns);
		
		return pageName;
    }
	
}
