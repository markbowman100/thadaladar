package com.markweb.logic;

import java.util.List;
import java.util.Map;

import com.markweb.objects.Adventure;
import com.markweb.objects.AdventureScene;
import com.markweb.objects.Campaign;

public interface AdventureLogic {
	
	public List<Campaign> getCampaigns(String username);
	
	public Adventure getAdventures(int playerAdventureId, int adventureId, String username);
	
	public Adventure getNextAdventure(int playerAdventureId, int playerSceneId, String username);
	
	public int updateSceneSelectedOption(AdventureScene scenes, String option);
	
	public int updateSceneUnselectedOptions(int playerId, int sceneId, int playerAdventureId);
	
	public int completeAdventures(int playerAdventureId);
	
	public List<String> getPlayers(int campaignId);
	
}
