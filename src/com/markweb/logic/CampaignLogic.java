package com.markweb.logic;

import java.util.List;

import com.markweb.objects.Campaign;
import com.markweb.objects.CreateCampaignFormBean;

public interface CampaignLogic {
	
	public List<Campaign> getMyCampaigns(String username);
	
	public List<Campaign> getAllOtherCampaigns(String username);
	
	public List<String> getPlayers(int campaignId);
	
	public int createCampaign(CreateCampaignFormBean campaign, String username);
	
	public int joinCampaign(int campaignId, String username);
	
}
