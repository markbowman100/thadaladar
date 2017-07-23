package com.markweb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.markweb.logic.AdventureLogic;
import com.markweb.logic.AdventureLogicImpl;
import com.markweb.objects.Adventure;
import com.markweb.objects.AdventureFormBean;
import com.markweb.objects.Campaign;
import com.markweb.objects.SceneOption;

@EnableWebMvc
@Controller
public class AdventureController {
	
	private AdventureLogic logic = new AdventureLogicImpl();
	private static Logger log = Logger.getLogger("com.markweb.controller");
	
	//Campaign Wireframe URL: https://wireframe.cc/DcS067
	
	@RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public String getAdventures(Model model, HttpSession session) {
		
		String pageName = "campaign";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		List<Campaign> campaigns = logic.getCampaigns((String) session.getAttribute("username")); 
		model.addAttribute("campaigns", campaigns);
		
		return pageName;
    }
	
	@RequestMapping(value = "/adventure{a}{b}", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getScenes(@RequestParam("a") int adventureId, @RequestParam("b") int playerAdventureId, @ModelAttribute AdventureFormBean command, BindingResult bindingresult, Model model, HttpSession session) {
		
		String pageName = "adventure";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		Adventure sessionAdventure = (Adventure) session.getAttribute("adventure");
		
		sessionAdventure = logic.getAdventures(playerAdventureId, adventureId); 
		session.setAttribute("adventure", sessionAdventure); 
		model.addAttribute("adventure", sessionAdventure);

		List<String> options = new ArrayList<String>();
		for (SceneOption option : sessionAdventure.getScene().getOptions()) {
			options.add(option.getTitle());
		}
		command.setOptions(options);
		
		return new ModelAndView(pageName, "command", command);
    }
	
	@RequestMapping(value = "/adventure{a}{b}", method = RequestMethod.POST)
    public @ResponseBody ModelAndView postScenes(@RequestParam("a") int adventureId, @RequestParam("b") int playerAdventureId, @ModelAttribute AdventureFormBean command, BindingResult bindingresult, Model model, HttpSession session) {
		
		String pageName = "adventure";
		
		if (session.getAttribute("username") == null) {
			pageName = "redirect:/";
		}
		
		Adventure sessionAdventure = (Adventure) session.getAttribute("adventure");
		int nextSceneId = 0;
		
		if (command.getSelectedOption() != null) {
			logic.updateSceneSelectedOption(sessionAdventure.getScene(), command.getSelectedOption());
		}
		else {
			if (sessionAdventure.getScene().getOptions().isEmpty()) {
				//TODO: Complete the scene
			}
		}
		
		for (SceneOption option : sessionAdventure.getScene().getOptions()) {
			if (command.getSelectedOption().equals(option.getTitle())) {
				nextSceneId = option.getNextSceneId();
			}
		}
		
		if (command.getSelectedOption() != null) {
			logic.updateSceneUnselectedOptions(sessionAdventure.getPlayerId(), nextSceneId, playerAdventureId);
		}
		
		if (nextSceneId != 0) {
			sessionAdventure = logic.getNextAdventure(playerAdventureId, nextSceneId); 
		}
		else {
			sessionAdventure = logic.getAdventures(playerAdventureId, adventureId); 
		}
		
		session.setAttribute("adventure", sessionAdventure);
		model.addAttribute("adventure", sessionAdventure);
		
		if (sessionAdventure.getScene() != null) {
			List<String> options = new ArrayList<String>();
			for (SceneOption option : sessionAdventure.getScene().getOptions()) {
				options.add(option.getTitle());
			}
			command.setOptions(options);
		}
		else {
			pageName = "campaigns";
		}
		
		return new ModelAndView(pageName, "command", command);
    }
	
}
