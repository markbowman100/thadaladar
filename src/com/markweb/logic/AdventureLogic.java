package com.markweb.logic;

import java.util.List;

import com.markweb.objects.Adventure;
import com.markweb.objects.AdventureScene;
import com.markweb.objects.Campaign;

public interface AdventureLogic {
	
	public List<Campaign> getCampaigns(String username);
	
	public Adventure getAdventures(int playerAdventureId, int adventureId);
	
	public Adventure getNextAdventure(int playerAdventureId, int playerSceneId);
	
	public int updateSceneSelectedOption(AdventureScene scenes, String option);
	
	public int updateSceneUnselectedOptions(int playerId, int sceneId, int playerAdventureId);
	
}
