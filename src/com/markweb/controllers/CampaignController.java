package com.markweb.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.markweb.logic.CampaignLogic;
import com.markweb.logic.CampaignLogicImpl;
import com.markweb.objects.Campaign;
import com.markweb.objects.CreateCampaignFormBean;

@EnableWebMvc
@Controller
public class CampaignController {
	
	private CampaignLogic logic = new CampaignLogicImpl();
	private static Logger log = Logger.getLogger("com.markweb.controller");
	
	//Campaign Wireframe URL: https://wireframe.cc/DcS067
	
	@RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public String getAdventures(Model model, HttpSession session) {
		
		String pageName = "campaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		List<Campaign> campaigns = logic.getCampaigns((String) session.getAttribute("username")); 
		
		for (Campaign campaign : campaigns) {
			List<String> players = logic.getPlayers(campaign.getId());
			campaign.setOtherPlayers(players);
		}
		
		model.addAttribute("campaigns", campaigns);
		
		return pageName;
    }
	
	@RequestMapping(value = "/createcampaign", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getNewCampaign(CreateCampaignFormBean command, BindingResult bindingresult, Model model, HttpSession session) {
		
		String pageName = "createcampaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		return new ModelAndView(pageName, "command", command);
    }
	
	@RequestMapping(value = "/createcampaign", method = RequestMethod.POST)
    public @ResponseBody ModelAndView postNewCampaign(@Valid CreateCampaignFormBean command, BindingResult bindingresult, Model model, HttpSession session) {
		
		String pageName = "createcampaign";
		ModelAndView modelAndView;
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		if (bindingresult.hasErrors()) {
			modelAndView =  new ModelAndView(pageName, "command", command);
		}
		else {
			//TODO: Have this submit the create campaign request
			int success = logic.createCampaign(command, (String) session.getAttribute("username")) ;
			if (success == 1) {
				pageName = "redirect:/campaign";
			}
			modelAndView = new ModelAndView(pageName); 
		}
		
		return modelAndView;
    }
	
	@RequestMapping(value = "/joincampaign", method = RequestMethod.GET)
    public String getJoinCampaign(Model model, HttpSession session) {
		
		String pageName = "joincampaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		return pageName;
    }
	
	@RequestMapping(value = "/editcampaign", method = RequestMethod.GET)
    public String getEditCampaign(Model model, HttpSession session) {
		
		String pageName = "editcampaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		return pageName;
    }
	
}
