package com.markweb.dao;

import java.util.List;
import java.util.Map;

public interface AdventureDao {
	
	public List<Map<String, Object>> getCampaigns(String username);
	
	public List<Map<String, Object>> getAdventures(int campaignId, int playerId);
	
	public List<Map<String, Object>> getScenes(int playerAdventureId);
	
	public List<Map<String, Object>> getNextScene(int nextSceneId, int playerId);
	
	public List<Map<String, Object>> getScenesOptions(int sceneId);
	
	public List<Map<String, Object>> getAdventure(int playerAdventureId);
	
	public List<Map<String, Object>> getPlayers(int campaignId);
	
	public int updateSceneSelectedOption(int sceneId, int playerSceneId, String option);
	
	public int updateSceneUnselectedOptions(int playerId, int nextSceneId, int playerAdventureId);
	
}
