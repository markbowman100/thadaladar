package com.markweb.logic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.markweb.dao.CampaignDao;
import com.markweb.dao.CampaignDaoImpl;
import com.markweb.objects.Adventure;
import com.markweb.objects.Campaign;
import com.markweb.objects.CreateCampaignFormBean;

public class CampaignLogicImpl implements CampaignLogic {
	
	CampaignDao dao = new CampaignDaoImpl();
	
	@Override
	public List<Campaign> getMyCampaigns(String username) {
		
		List<Map<String, Object>> rawCampaigns = dao.getMyCampaigns(username);
		List<Campaign> campaigns = getCampaign(rawCampaigns);
		
		// Each campaign has a list of adventures. Each adventure has a list of scenes. Each scene has a list
		// of options. The first loop is to set adventures. The second loop is to set the scenes. The third
		// loop is to set the options.
		for (Campaign campaign: campaigns) {
			List<Adventure> adventures = getAdventure(campaign.getId(), campaign.getPlayerId());
				
			campaign.setAdventures(adventures);
		}
		
		return campaigns;
	}
	
	@Override
	public List<Campaign> getAllOtherCampaigns(String username) {
		// Getting all campaigns that are accepting players and aren't assigned to current user.
		List<Map<String, Object>> rawCampaigns = dao.getAllOtherCampaigns(username);
		List<Campaign> campaigns = getCampaign(rawCampaigns);
		return campaigns;
	}
	
	@Override
	public List<String> getPlayers(int campaignId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rawPlayers = dao.getPlayers(campaignId);
		List<String> players = new ArrayList<String>();
		
		for (Map<String, Object> player : rawPlayers) {
			players.add((String) player.get("Username"));
		}
		
		return players;
	}
	
	private List<Campaign> getCampaign(List<Map<String, Object>> rawCampaigns) {
		
		List<Campaign> currentCampaigns = new ArrayList<Campaign>(); 
		
		for(Map<String, Object> raw : rawCampaigns) {
			Campaign campaign = new Campaign();
			
			if (raw.get("PlayerId") != null) {
				campaign.setPlayerId((int) raw.get("PlayerId"));
			}
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
	
	private List<Map<String, Object>> getPlayer(int campaignId) {
		return dao.getPlayers(campaignId);
	}
	
	private List<Adventure> mapAdventures(List<Map<String, Object>> rawAdventures) {
		
		List<Adventure> currentAdventures = new ArrayList<Adventure>();
		
		
		for(Map<String, Object> raw : rawAdventures) {
			Adventure adventure = new Adventure();
			
			if (raw.get("RowId") != null) {
				adventure.setPlayerAdventureId((int) raw.get("RowId"));
			}
			if (raw.get("AdventureId") != null) {
				adventure.setAdventureId((int) raw.get("AdventureId"));
			}
			if (raw.get("PlayerId") != null) {
				adventure.setPlayerId((int) raw.get("PlayerId"));
			}
			if (raw.get("Title") != null) {
				adventure.setTitle((String) raw.get("Title"));
			}
			if (raw.get("Description") != null) {
				adventure.setDescription((String) raw.get("Description"));
			}
			if (raw.get("StartDate") != null) {
				Date sqlDate = (Date) raw.get("StartDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				adventure.setStartDate(javaDate);
			}
			if (raw.get("EndDate") != null) {
				Date sqlDate = (Date) raw.get("EndDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				adventure.setEndDate(javaDate);
			}
			if (raw.get("DueDate") != null) {
				Date sqlDate = (Date) raw.get("DueDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				adventure.setDueDate(javaDate);
			}
			
			currentAdventures.add(adventure);
		}
		
		return currentAdventures;
	}
	
	private List<Adventure> getAdventure(int campaignId, int playerId) {
		
		List<Adventure> currentAdventures = new ArrayList<Adventure>(); 
		
		List<Map<String, Object>> rawAdventures = dao.getAdventures(campaignId, playerId);
		
		currentAdventures = mapAdventures(rawAdventures);
		
		return currentAdventures;
	}

	@Override
	public int createCampaign(CreateCampaignFormBean campaign, String username) {
		return dao.createCampaign(campaign, username);
	}

	@Override
	public int joinCampaign(int campaignId, String username) {
		int success = dao.joinCampaign(campaignId, username);
		return success;
	}

}
