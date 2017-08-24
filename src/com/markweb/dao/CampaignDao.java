package com.markweb.dao;

import java.util.List;
import java.util.Map;

import com.markweb.objects.CreateCampaignFormBean;

public interface CampaignDao {
	
	public List<Map<String, Object>> getMyCampaigns(String username);
	
	public List<Map<String, Object>> getAllOtherCampaigns(String username);
	
	public List<Map<String, Object>> getPlayers(int campaignId);
	
	public List<Map<String, Object>> getAdventures(int campaignId, int playerId);
	
	public int createCampaign(CreateCampaignFormBean campaign, String username);
	
	public int joinCampaign(int campaignId, String username);

}
