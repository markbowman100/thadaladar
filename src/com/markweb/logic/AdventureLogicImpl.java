package com.markweb.logic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.markweb.dao.AdventureDao;
import com.markweb.dao.AdventureDaoImpl;
import com.markweb.objects.Adventure;
import com.markweb.objects.AdventureScene;
import com.markweb.objects.Campaign;
import com.markweb.objects.SceneOption;

public class AdventureLogicImpl implements AdventureLogic {
	
	AdventureDao dao = new AdventureDaoImpl();
	
	@Override
	public List<Campaign> getCampaigns(String username) {
		List<Campaign> campaigns = getCampaign(username);
		
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
	public Adventure getAdventures(int playerAdventureId, int adventureId, String username) {
		List<Map<String, Object>> rawAdventures = dao.getAdventure(playerAdventureId, username);
		List<Adventure> currentAdventures = mapAdventures(rawAdventures);
		Adventure adventure = new Adventure();
		if (!currentAdventures.isEmpty()) {
			adventure = currentAdventures.get(0);
			List<AdventureScene> scenes = getScene(playerAdventureId);
			
			for (AdventureScene scene: scenes) {
				List<SceneOption> options = getSceneOption(scene.getSceneId());
				scene.setOptions(options);
			}
			
			// There should only ever be one scene per adventure here. If not, something unexpected happened.
			adventure.setScenes(scenes.get(0));
		}
		return adventure;
	}
	
	@Override
	public Adventure getNextAdventure(int playerAdventureId, int playerSceneId, String username) {
		List<Map<String, Object>> rawAdventures = dao.getAdventure(playerAdventureId, username);
		List<Adventure> currentAdventures = mapAdventures(rawAdventures);
		Adventure adventure = currentAdventures.get(0);
		List<AdventureScene> scenes = getNextScene(playerSceneId, adventure.getPlayerId());
		
		for (AdventureScene scene: scenes) {
			List<SceneOption> options = getSceneOption(scene.getSceneId());
			scene.setOptions(options);
		}
		
		// There should only ever be one scene per adventure here. If not, something unexpected happened.
		adventure.setScene(scenes.get(0));
		return adventure;
	}
	
	@Override
	public int updateSceneSelectedOption(AdventureScene scene, String option) {
		//TODO: Also update unselected options
		return dao.updateSceneSelectedOption(scene.getSceneId(), scene.getPlayerSceneId(), option);
	}
	
	@Override
	public int updateSceneUnselectedOptions(int playerId, int nextSceneId, int playerAdventureId) {
		//TODO: Also update unselected options
		return dao.updateSceneUnselectedOptions(playerId, nextSceneId, playerAdventureId);
	}
	
	@Override
	public int completeAdventures(int playerAdventureId) {
		// TODO Auto-generated method stub
		return dao.completeAdventure(playerAdventureId);
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
	
	private List<Campaign> getCampaign(String username) {
		
		List<Campaign> currentCampaigns = new ArrayList<Campaign>(); 
		
		List<Map<String, Object>> rawCampaigns = dao.getCampaigns(username);
		
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
	
	private List<Adventure> getAdventure(int campaignId, int playerId) {
		
		List<Adventure> currentAdventures = new ArrayList<Adventure>(); 
		
		List<Map<String, Object>> rawAdventures = dao.getAdventures(campaignId, playerId);
		
		currentAdventures = mapAdventures(rawAdventures);
		
		return currentAdventures;
	}
	
	private List<AdventureScene> getScene(int playerAdventureId) {
		
		List<Map<String, Object>> rawScenes = dao.getScenes(playerAdventureId);
		
		List<AdventureScene> currentScenes = mapScene(rawScenes);
		
		return currentScenes;
	}
	
	private List<AdventureScene> getNextScene(int playerSceneId, int playerId) {
		
		List<Map<String, Object>> rawScenes = dao.getNextScene(playerSceneId, playerId);
		
		List<AdventureScene> currentScenes = mapScene(rawScenes);
		
		return currentScenes;
	}
	
	private List<AdventureScene> mapScene(List<Map<String, Object>> rawScenes) {
		List<AdventureScene> currentScenes = new ArrayList<AdventureScene>(); 
		for(Map<String, Object> raw : rawScenes) {
			AdventureScene scene = new AdventureScene();
			
			if (raw.get("RowId") != null) {
				scene.setPlayerSceneId((int) raw.get("RowId"));
			}
			if (raw.get("SceneId") != null) {
				scene.setSceneId((int) raw.get("SceneId"));
			}
			if (raw.get("PlayerId") != null) {
				scene.setPlayerId((int) raw.get("PlayerId"));
			}
			if (raw.get("Title") != null) {
				scene.setTitle((String) raw.get("Title"));
			}
			if (raw.get("Story") != null) {
				scene.setStory((String) raw.get("Story"));
			}
			if (raw.get("StartDate") != null) {
				Date sqlDate = (Date) raw.get("StartDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				scene.setStartDate(javaDate);
			}
			if (raw.get("EndDate") != null) {
				Date sqlDate = (Date) raw.get("EndDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				scene.setEndDate(javaDate);
			}
			if (raw.get("DueDate") != null) {
				Date sqlDate = (Date) raw.get("DueDate");
				LocalDate javaDate = sqlDate.toLocalDate();
				scene.setDueDate(javaDate);
			}
			
			currentScenes.add(scene);
		}
		return currentScenes;
	}
	
	private List<SceneOption> getSceneOption(int sceneId) {
		
		List<SceneOption> currentSceneOptions = new ArrayList<SceneOption>(); 
		
		List<Map<String, Object>> rawScenes = dao.getScenesOptions(sceneId);
		
		for(Map<String, Object> raw : rawScenes) {
			SceneOption sceneOption = new SceneOption();
			
			if (raw.get("Title") != null) {
				sceneOption.setTitle((String) raw.get("Title"));
			}
			if (raw.get("NextSceneId") != null) {
				sceneOption.setNextSceneId((int) raw.get("NextSceneId"));
			}
			
			currentSceneOptions.add(sceneOption);
		}
		
		return currentSceneOptions;
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
	
}
