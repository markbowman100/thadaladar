package com.markweb.logic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.markweb.dao.AdventureDao;
import com.markweb.dao.AdventureDaoImpl;
import com.markweb.objects.Adventure;
import com.markweb.objects.Campaign;

public class AdventureLogicImpl implements AdventureLogic {
	
	AdventureDao dao = new AdventureDaoImpl();
	
	public List<Campaign> getCampaign(String username) {
		
		List<Campaign> currentCampaigns = new ArrayList<Campaign>(); 
		
		List<Map<String, Object>> rawCampaigns = dao.getCampaigns(username);
		
		for(Map<String, Object> raw : rawCampaigns) {
			Campaign campaign = new Campaign();
			
			if (raw.get("CampaignId") != null) {
				campaign.setId((int) raw.get("CampaignId"));
			}
			if (raw.get("Title") != null) {
				campaign.setTitle((String) raw.get("Title"));
			}
			if (raw.get("Description") != null) {
				campaign.setDescription((String) raw.get("Description"));
			}
			if (raw.get("Game") != null) {
				campaign.setGame((String) raw.get("Game"));
			}
			if (raw.get("Version") != null) {
				campaign.setVersion((String) raw.get("Version"));
			}
			if (raw.get("AcceptingPlayers") != null) {
				if ((int) raw.get("AcceptingPlayers") == 1) {
					campaign.setAcceptingPlayers(true);
				}
				else {
					campaign.setAcceptingPlayers(false);
				}
			}
			if (raw.get("StartDate") != null) {
				Date sqlDate = (Date) raw.get("StartDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				campaign.setStartDate(javaDate);
			}
			if (raw.get("EndDate") != null) {
				Date sqlDate = (Date) raw.get("EndDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				campaign.setStartDate(javaDate);
			}
			
			currentCampaigns.add(campaign);
		}
		
		return currentCampaigns;
	}
	
	private List<Adventure> getAdventure(int campaignId, String username) {
		
		List<Adventure> currentAdventures = new ArrayList<Adventure>(); 
		
		List<Map<String, Object>> rawAdventures = dao.getAdventures(campaignId, username);
		
		for(Map<String, Object> raw : rawAdventures) {
			Adventure adventure = new Adventure();
			
			
			
			
		}
		
		return null;
	}
	
}
