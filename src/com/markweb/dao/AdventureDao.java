package com.markweb.dao;

import java.util.List;
import java.util.Map;

public interface AdventureDao {
	
	public List<Map<String, Object>> getCampaigns(String username);
	
	public List<Map<String, Object>> getAdventures(int campaignId, String username);
	
	public List<Map<String, Object>> getScenes(int adventureId, String username);
	
	public List<Map<String, Object>> getScenesOptions(int sceneId, String username);
	
	public List<Map<String, Object>> getPlayers(int campaignId, String username);
	
}
