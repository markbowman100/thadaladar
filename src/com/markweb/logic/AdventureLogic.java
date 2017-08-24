package com.markweb.logic;

import com.markweb.objects.Adventure;
import com.markweb.objects.AdventureScene;

public interface AdventureLogic {
	
	public Adventure getAdventures(int playerAdventureId, int adventureId, String username);
	
	public Adventure getNextAdventure(int playerAdventureId, int playerSceneId, String username);
	
	public int updateSceneSelectedOption(AdventureScene scenes, String option);
	
	public int updateSceneUnselectedOptions(int playerId, int sceneId, int playerAdventureId);
	
	public int completeAdventures(int playerAdventureId);
	
}
